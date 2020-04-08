package main.java.generateReport;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import database.DatabaseHelper;
import domain.Commission;
import domain.Rate;
import main.java.domain.Payment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateIndividualGlobalSalesReport {
    private static Connection con;
    private static database.DatabaseHelper db = new DatabaseHelper();
    private static String sql;
    public static List<Payment> paymentList;
    public static Rate rate;
    public static PdfPTable documentsTable2;
    public static PdfPTable cashTable2;
    public static PdfPTable cardTable2;
    public static PdfPTable totalAmountPaidTable2;
    public static PdfPTable commissionTable2;


    //floats need to be passed as stings in tables
    public GenerateIndividualGlobalSalesReport(){

    }
    public static void generateIndividualReport(String ticketType ,int staffID, String currencyCode){
        //add to payment list
        addToPaymentList(ticketType,staffID,currencyCode);
        //create Documents Table
        try{
            String[] columnArray = {"N","Original\nIssued\nNumber","USD","USD/"+currencyCode,currencyCode,"Tax","Total\nDocument's\nAmount"};
            String[] cashColumnArray = {"N","Cash"};
            String[] cardColumnArray = {"N","Full CC Number","USD",currencyCode};

            Document document = new Document(PageSize.A2.rotate());
            PdfWriter.getInstance(document,new FileOutputStream("report/report.pdf"));
            document.open();

            //creating the tables
            PdfPTable documentsTable = new PdfPTable(1);
            documentsTable.addCell(new Paragraph("Air Via Documents"));
            documentsTable2 = new PdfPTable(7);

            PdfPTable cashCardTable = new PdfPTable(3);
            PdfPTable cashTable = new PdfPTable(1);
            cashTable.addCell(new Paragraph("Forms of Payments"));
            cashTable2 = new PdfPTable(2);
            PdfPTable cardTable = new PdfPTable(1);
            cardTable.addCell(new Paragraph("Forms of Payments"));
            cardTable2 = new PdfPTable(4);
            PdfPTable totalAmountPaidTable = new PdfPTable(1);
            totalAmountPaidTable.addCell(new Paragraph("Forms of Payment"));
            totalAmountPaidTable2 = new PdfPTable(1);
            totalAmountPaidTable2.addCell(new Paragraph("Total\nAmount\nPaid"));
            totalAmountPaidTable.addCell(totalAmountPaidTable2);

            PdfPTable commissionTable = new PdfPTable(1);
            commissionTable.addCell(new Paragraph("Commissions"));
            commissionTable.addCell(new Paragraph("Assessable Amounts"));


            documentsTable.addCell(documentsTable2);
            for (int i = 0; i <columnArray.length ; i++) {
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



            addToDocumentTable();

            addToCashAndCardTable();

            String netAmount = addToCommissionTable(ticketType);

            cashCardTable.addCell(cashTable);
            cashCardTable.addCell(cardTable);
            cashCardTable.addCell(totalAmountPaidTable);
            commissionTable.addCell(commissionTable2);


            con = db.getConnection();
            sql = "SELECT * FROM staff WHERE ID=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,staffID);
            ResultSet rs = stm.executeQuery();
            rs.next();

            document.add(new Paragraph(ticketType
                    + " sales report\n"
                    + rs.getString("Forename")
                    + " "
                    + rs.getString("Surname")
                    + " "
                    + staffID));
            document.add(new Phrase("\n"));
            document.add(documentsTable);
            document.add(new Phrase("\n"));
            document.add(cashCardTable);
            document.add(new Phrase("\n"));
            document.add(commissionTable);
            document.add(new Paragraph("Total nett amount for bank remmittence to air via  " + netAmount));
            document.close();
        }catch (SQLException | DocumentException | IOException ex){
            ex.printStackTrace();
        }

    }

    public static void addToPaymentList(String ticketType, int staffID, String currencyCode){
        try {
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
            //get the payments
            if (ticketType.equals("interline") || ticketType.equals("domestic")){
                sql = "SELECT * FROM payment WHERE StaffID=? AND TicketType=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1,staffID);
                stm.setString(2,ticketType);
                rs = stm.executeQuery();
            }else if(ticketType.equals("global")){
                //office manager and travel advisor
                sql = "SELECT * FROM payment ";

            }


            paymentList = new ArrayList<>();
            while(rs.next()){
                paymentList.add(new Payment(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getString(10),
                        rs.getLong(11)));
            }






        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void addToDocumentTable(){
        int ticketNum = 0;
        Float usdSum = 0f;
        Float currencyCodeSum = 0f;
        Float taxSum = 0f;
        Float totalSum = 0f;


        for (int i = 0; i <paymentList.size() ; i++) {
            Payment p = paymentList.get(i);
            documentsTable2.addCell(new Paragraph(p.getPaymentID()));
            ticketNum++;
            documentsTable2.addCell(new Paragraph(p.getBlankID()));
            //convert to USD
            Float usdRate = getUsdRate(p);
            documentsTable2.addCell(new Paragraph(p.getAmountAfterTax()*usdRate));
            usdSum += p.getAmountAfterTax()*usdRate;
            documentsTable2.addCell(new Paragraph(rate.getExchangeRate()));
            documentsTable2.addCell(new Paragraph(p.getAmountAfterTax()*usdRate/rate.getExchangeRate()));
            currencyCodeSum += p.getAmountAfterTax()*usdRate/rate.getExchangeRate();
            documentsTable2.addCell(new Paragraph((p.getAmount()-p.getAmountAfterTax())*usdRate/rate.getExchangeRate()));
            taxSum += (p.getAmount()-p.getAmountAfterTax())*usdRate/rate.getExchangeRate();
            documentsTable2.addCell(new Paragraph(p.getAmount()*usdRate/rate.getExchangeRate()));
            totalSum += p.getAmount()*usdRate/rate.getExchangeRate();
            documentsTable2.addCell(new Paragraph(ticketNum));
            documentsTable2.addCell(new Paragraph(" "));
            documentsTable2.addCell(new Paragraph(usdSum));
            documentsTable2.addCell(new Paragraph(" "));
            documentsTable2.addCell(new Paragraph(currencyCodeSum));
            documentsTable2.addCell(new Paragraph(taxSum));
            documentsTable2.addCell(new Paragraph(totalSum));

        }
    }
    public static void addToCashAndCardTable(){
        Float cashTotalSum = 0F;
        Float usdSum = 0F;
        Float currencySum = 0F;
        Float cashCardTotal = 0F;

        for (int i = 0; i <paymentList.size() ; i++) {
            Payment p = paymentList.get(i);
            Float usdRate = getUsdRate(p);
            if (p.getType().equals("cash")){
                cashTable2.addCell(new Paragraph(p.getPaymentID()));
                cashTable2.addCell(new Paragraph(p.getAmount()*usdRate/rate.getExchangeRate()));
                cashTotalSum += p.getAmount()*usdRate/rate.getExchangeRate();
                totalAmountPaidTable2.addCell(new Paragraph(p.getAmount()*usdRate/rate.getExchangeRate()));

            }else {
                cardTable2.addCell(new Paragraph(p.getPaymentID()));
                //card details primary key is cc number
                Long cardNumber = getCardNumber(p);
                cardTable2.addCell(new Paragraph(cardNumber));
                cardTable2.addCell(new Paragraph(p.getAmount()*usdRate));
                usdSum += p.getAmount()*usdRate;
                cardTable2.addCell(new Paragraph(p.getAmount()*usdRate/rate.getExchangeRate()));
                currencySum += p.getAmount()*usdRate/rate.getExchangeRate();
                totalAmountPaidTable2.addCell(new Paragraph(p.getAmount()*usdRate/rate.getExchangeRate()));

            }
        }
        cashTable2.addCell(new Paragraph(" "));
        cashTable2.addCell(new Paragraph(cashTotalSum));
        cardTable2.addCell(new Paragraph(" "));
        cardTable2.addCell(new Paragraph(usdSum));
        cardTable2.addCell(new Paragraph(currencySum));
        cardTable2.addCell(new Paragraph(cashTotalSum + currencySum));

    }
    public static String addToCommissionTable(String ticketType){
        try{
            List<Commission> commissionList = new ArrayList<>();
            con = db.getConnection();
            if (ticketType.equals("interline")){
                sql = "SELECT * FROM commission WHERE Type=444 OR Type=440 OR Type=444 OR Type=420 OR Type=451 OR Type=452";

            }else if(ticketType.equals("domestic")){
                sql = "SELECT * FROM commission WHERE Type=201 OR Type=101";

            }else if (ticketType.equals("global")){
                sql = "SELECT * FROM commission";

            }
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                commissionList.add(new Commission(rs.getInt(1),
                        rs.getFloat(2),
                        rs.getInt(3)));

            }
            commissionTable2 = new PdfPTable(commissionList.size());

            //add commission rate to table
            Iterator<Commission> it = commissionList.iterator();
            Float commissionSum = 0F;
            while(it.hasNext()){
                Commission c = it.next();
                PdfPTable singleCommissionTable = new PdfPTable(1);
                singleCommissionTable.addCell(new Paragraph(String.valueOf(c.getCommission_rate() + "%")));
                List<String> strings = new ArrayList<>();
                Float afterTaxSum = 0F;
                Float commissionTotal = 0F;
                for (int i = 0; i < paymentList.size(); i++) {
                    Payment p = paymentList.get(i);
                    if(p.getCommissionID() == c.getCommission_id()){
                        strings.add(String.valueOf(p.getAmountAfterTax()));
                        afterTaxSum += p.getAmountAfterTax();
                    }
                }
                singleCommissionTable.addCell(new Paragraph(commissionStringbuilder(strings)));
                singleCommissionTable.addCell(new Paragraph(afterTaxSum));
                commissionTotal = (afterTaxSum/100F)*c.getCommission_rate();
                singleCommissionTable.addCell(new Paragraph(commissionTotal));
                singleCommissionTable.addCell(new Paragraph(afterTaxSum-commissionTotal));
                commissionSum += commissionTotal;
                strings.clear();
                commissionTable2.addCell(singleCommissionTable);
            }

            Float beforeTaxSum = 0F;
            for (int i = 0; i <paymentList.size() ; i++) {
                Payment p = paymentList.get(i);
                Float usdRate = getUsdRate(p);
                beforeTaxSum += p.getAmount()*usdRate/rate.getExchangeRate();
            }

            return String.valueOf(beforeTaxSum - commissionSum);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return "0";
    }
    public static Float getUsdRate(Payment p){
        Float usdRate = 0f;
        try{
            con = db.getConnection();
            sql = "SELECT USDRate from exchange_rate WHERE RateID=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,p.getRateID());
            ResultSet rs = stm.executeQuery();
            usdRate = rs.getFloat("USDRate");
            return usdRate;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return usdRate;
    }
    public static Long getCardNumber(Payment p){
        Long cardNumber = 0L;
        try{
            con = db.getConnection();
            sql = "SELECT Number FROM card_details WHERE CustomerID=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,p.getCustomerID());
            ResultSet rs = stm.executeQuery();
            cardNumber = rs.getLong("Number");
            return cardNumber;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return cardNumber;
    }
    public static String commissionStringbuilder(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list){
            stringBuilder.append(s);
            stringBuilder.append("\n");


        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {

        GenerateIndividualGlobalSalesReport.generateIndividualReport("domestic",3,"CAD");

    }
}

