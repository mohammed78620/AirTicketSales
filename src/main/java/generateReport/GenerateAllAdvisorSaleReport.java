package main.java.generateReport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import database.DatabaseHelper;
import domain.Commission;
import domain.Rate;
import main.java.domain.Payment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateAllAdvisorSaleReport extends GenerateIndividualGlobalSalesReport {
    private static Connection con;
    private static database.DatabaseHelper db = new DatabaseHelper();
    private static String sql;
    private static Rate rate;
    private static List<Integer> staffIDList;
    private static PdfPTable documentsTable2;
    private static PdfPTable cashTable2;
    private static PdfPTable cardTable2;
    private static PdfPTable totalAmountPaidTable2;


    public GenerateAllAdvisorSaleReport(){

    }
    public static void generateGlobalReport(String ticketType,String currencyCode,int staffID){
        try{
            String[] columnArray = {"N","AGNT\nNMBR","Sold tickets","Fare Amount","Tax","Total\ndocuments\namount"};
            //staffid represents N
            String[] cashColumnArray = {"Advisor Number","Cash"};
            String[] cardColumnArray = {"USD",currencyCode};
            Document document = new Document(PageSize.A2.rotate());
            PdfWriter.getInstance(document, new FileOutputStream("report/report.pdf"));
            document.open();

            //creating the tables
            PdfPTable documentsTable = new PdfPTable(1);
            documentsTable.addCell(new Paragraph("Air Via Documents"));
            documentsTable2 = new PdfPTable(6);

            PdfPTable cashCardTable = new PdfPTable(3);
            PdfPTable cashTable = new PdfPTable(1);
            cashTable.addCell(new Paragraph("Forms of Payments Cash"));
            cashTable2 = new PdfPTable(2);
            PdfPTable cardTable = new PdfPTable(1);
            cardTable.addCell(new Paragraph("Forms of Payments Credit Card"));
            cardTable2 = new PdfPTable(2);
            PdfPTable totalAmountPaidTable = new PdfPTable(1);
            totalAmountPaidTable.addCell(new Paragraph("Forms of Payment"));
            totalAmountPaidTable2 = new PdfPTable(1);
            totalAmountPaidTable2.addCell(new Paragraph("Total Amount Paid"));
            totalAmountPaidTable.addCell(totalAmountPaidTable2);

            PdfPTable commissionTable = new PdfPTable(1);
            commissionTable.addCell(new Paragraph("Commissions"));
            commissionTable.addCell(new Paragraph("Assessable Amounts"));


            documentsTable.addCell(documentsTable2);
            for (int i = 0; i < columnArray.length; i++) {
                documentsTable2.addCell(new Paragraph(columnArray[i]));
            }
            cashTable.addCell(cashTable2);
            for (int i = 0; i < cashColumnArray.length; i++) {
                cashTable2.addCell(new Paragraph(cashColumnArray[i]));
            }
            cardTable.addCell(cardTable2);
            for (int i = 0; i <cardColumnArray.length ; i++) {
                cardTable2.addCell(new Paragraph(cardColumnArray[i]));
            }

            addToDocumentsTable(ticketType,currencyCode);

            addToCashAndCardTable(ticketType,currencyCode);

            String netAmount = addToCommissionTable(ticketType,currencyCode);



            cashCardTable.addCell(cashTable);
            cashCardTable.addCell(cardTable);
            cashCardTable.addCell(totalAmountPaidTable);
            commissionTable.addCell(commissionTable2);

            document.add(new Phrase("\n"));
            document.add(documentsTable);
            document.add(new Phrase("\n"));
            document.add(cashCardTable);
            document.add(new Phrase("\n"));
            document.add(commissionTable);
            document.add(new Phrase("\n"));
            document.add(new Paragraph("Total nett amount for bank remmittence to air via  " + netAmount));
            document.close();

            addToReport(staffID,ticketType);
        }catch(IOException | DocumentException ex){
            ex.printStackTrace();
        }
    }
    public static void setStaffIDList(String ticketType){
        staffIDList = new ArrayList<>();
        try{
            con = db.getConnection();
            sql = "SELECT DISTINCT ID" +
                    " FROM staff" +
                    " INNER JOIN payment" +
                    " ON staff.ID = payment.StaffID AND" +
                    " staff.Type = 'ta' AND" +
                    " payment.TicketType=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,ticketType);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                staffIDList.add(rs.getInt(1));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void addToDocumentsTable(String ticketType, String currencyCode){
        setStaffIDList(ticketType);

        int advisorNumber = 0;
        int ticketSum = 0;
        Float fareSum = 0F;
        Float taxSum = 0F;
        Float beforeTaxSum = 0F;
        for (int i = 0; i <staffIDList.size() ; i++) {
            addToPaymentList(ticketType, staffIDList.get(i),currencyCode);
            documentsTable2.addCell(new Paragraph(String.valueOf(advisorNumber)));
            documentsTable2.addCell(new Paragraph(String.valueOf(staffIDList.get(i))));
            documentsTable2.addCell(new Paragraph(String.valueOf(paymentList.size())));
            ticketSum += paymentList.size();
            Iterator<Payment> paymentIterator = paymentList.iterator();
            Float fareAmount = 0F;
            Float taxAmount = 0F;
            Float beforeTax = 0F;
            while (paymentIterator.hasNext()){
                Payment p = paymentIterator.next();
                fareAmount += p.getAmountAfterTax();
                taxAmount += p.getAmount()-p.getAmountAfterTax();
                beforeTax += p.getAmount();

            }
            fareSum += fareAmount;
            taxSum += taxAmount;
            beforeTaxSum += beforeTax;
            documentsTable2.addCell(new Paragraph(String.valueOf(fareAmount)));
            documentsTable2.addCell(new Paragraph(String.valueOf(taxAmount)));
            documentsTable2.addCell(new Paragraph(String.valueOf(beforeTax)));






            advisorNumber++;
            paymentList.clear();
        }
        documentsTable2.addCell(new Paragraph(String.valueOf("total: " + advisorNumber)));
        documentsTable2.addCell(new Paragraph(" "));
        documentsTable2.addCell(new Paragraph(String.valueOf("total: " + ticketSum)));
        documentsTable2.addCell(new Paragraph(String.valueOf("total: " + fareSum)));
        documentsTable2.addCell(new Paragraph(String.valueOf("total: " + taxSum)));
        documentsTable2.addCell(new Paragraph(String.valueOf("total: " + beforeTaxSum)));


    }
    public static void addToCashAndCardTable(String ticketType, String currencyCode){
        getLocalRate(currencyCode);
        Float cashSum = 0F;
        Float cardSum = 0F;
        Float totalSum = 0F;
        for (int i = 0; i < staffIDList.size(); i++) {
            addToPaymentList(ticketType,staffIDList.get(i),currencyCode);
            Iterator<Payment> paymentIterator = paymentList.iterator();
            Float cashTotal = 0F;
            Float cardTotal = 0F;
            Float total = 0F;
            while (paymentIterator.hasNext()){
                Payment p = paymentIterator.next();
                Float usdRate = getUsdRate(p);

                if (p.getType().equals("Cash")){
                    cashTotal += p.getAmount()*usdRate;
                }else if(p.getType().equals("Card")){
                    // add card details
                    cardTotal+=p.getAmount()*usdRate;
                }

            }
            cashTable2.addCell(new Paragraph(String.valueOf(staffIDList.get(i))));
            cashTable2.addCell(new Paragraph(String.valueOf(cashTotal/rate.getExchangeRate())));

            cardTable2.addCell(new Paragraph(String.valueOf(cardTotal)));
            cardTable2.addCell(new Paragraph(String.valueOf(cardTotal/rate.getExchangeRate())));
            total += cashTotal + cardTotal;
            totalAmountPaidTable2.addCell(new Paragraph(String.valueOf(total/rate.getExchangeRate())));

            cashSum += cashTotal;
            cardSum += cardTotal;
            totalSum += total;
        }
        cashTable2.addCell(new Paragraph(" "));
        cashTable2.addCell(new Paragraph(String.valueOf("total: " + cashSum/rate.getExchangeRate())));

        cardTable2.addCell(new Paragraph(String.valueOf("total: " +cardSum)));
        cardTable2.addCell(new Paragraph(String.valueOf("total: " +cardSum/rate.getExchangeRate())));

        totalAmountPaidTable2.addCell(new Paragraph(String.valueOf("total: " +totalSum/rate.getExchangeRate())));
        paymentList.clear();
    }
    public static String addToCommissionTable(String ticketType,String currencyCode){
        try{
            List<Commission> commissionList = new ArrayList<>();
            con = db.getConnection();
            if (ticketType.equals("interline")){
                sql = "SELECT * FROM commission WHERE Type=444 OR Type=440 OR Type=444 OR Type=420 OR Type=451 OR Type=452";

            }else if (ticketType.equals("domestic")){
                sql = "SELECT * FROM commission WHERE Type=201 OR Type=101";
            }
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                commissionList.add(new Commission(rs.getInt(1),
                        rs.getFloat(2),
                        rs.getInt(3)));
            }
            commissionTable2 = new PdfPTable(commissionList.size());

            //add to commission rate table
            Iterator<Commission> it = commissionList.iterator();
            Float afterTaxSum = 0F;
            Float commissionSum = 0F;
            while (it.hasNext()){
                Commission c = it.next();
                PdfPTable singleCommissionTable = new PdfPTable(2);
                singleCommissionTable.addCell(new Paragraph("Advisor Number"));
                singleCommissionTable.addCell(new Paragraph(String.valueOf(c.getCommission_rate() + "%")));

                Float afterTaxTotal = 0F;
                Float commissionTotal = 0F;
                for (int i = 0; i <staffIDList.size() ; i++) {
                    addToPaymentList(ticketType,staffIDList.get(i),currencyCode);
                    for (int j = 0; j < paymentList.size(); j++) {
                        Payment p = paymentList.get(i);
                        if (p.getCommissionID() == c.getCommission_id()){
                            afterTaxTotal += p.getAmountAfterTax();
                        }
                    }
                    singleCommissionTable.addCell(new Paragraph(String.valueOf(staffIDList.get(i))));
                    singleCommissionTable.addCell(new Paragraph(String.valueOf(afterTaxTotal)));
                    afterTaxSum += afterTaxTotal;
                }
                singleCommissionTable.addCell(new Paragraph(" "));
                singleCommissionTable.addCell(new Paragraph(String.valueOf("Total: " + afterTaxSum)));
                commissionTotal += (afterTaxSum/100F)*c.getCommission_rate();
                singleCommissionTable.addCell(new Paragraph(" "));
                singleCommissionTable.addCell(new Paragraph(String.valueOf("Total commission Amount: " + (afterTaxSum/100F)*c.getCommission_rate())));
                commissionSum += commissionTotal;
                commissionTable2.addCell(singleCommissionTable);
            }
            Float beforeTaxSum = 0F;
            for (int i = 0; i <staffIDList.size(); i++) {
                addToPaymentList(ticketType,staffIDList.get(i),currencyCode);
                for (int j = 0; j < paymentList.size(); j++) {
                    Payment p = paymentList.get(j);
                    if (p.getType().equals("Cash") || p.getType().equals("Card")) {
                        Float usdRate = getUsdRate(p);
                        beforeTaxSum += p.getAmount() * usdRate / rate.getExchangeRate();
                    }

                }
            }
            return String.valueOf(beforeTaxSum - commissionSum);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return "0";
    }
    public static void getLocalRate(String currencyCode){
        try{
            //get the local rate
            con = db.getConnection();
            sql = "SELECT * FROM exchange_rate WHERE CurrencyCode=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, currencyCode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                rate = new Rate(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }



    public static void main(String[] args) {
        
    }

}
