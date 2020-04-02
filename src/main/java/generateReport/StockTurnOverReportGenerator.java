package generateReport;

import com.itextpdf.maven.itextdoc.ItextDocMojo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import database.DatabaseHelper;
import domain.Blank;

import javax.annotation.processing.FilerException;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class StockTurnOverReportGenerator {
    private static DatabaseHelper db = new DatabaseHelper();
    private static Connection con;
    private static FileWriter fileWriter;
    private static List<Blank> blankList = new ArrayList<>();
    private static List<Blank> assignedBlanklist = new ArrayList<>();
    private static final List<Integer> typesOfBlanks = Arrays.asList(101,201,420,440,444);
    private static HashMap<Integer,List<Blank>>  blankHashMap = new HashMap<>();
    private static StringBuilder s;
    private static List<String> amountList;
    private static List<String> codeList;
    private static Iterator it;
    private static Iterator it2;
    private static Timestamp endDate;
    private static Timestamp startDate;
    private static PdfPCell fromToCell1;
    private static PdfPCell amountCell1;
    private static PdfPCell codeCell1;
    private static PdfPCell fromToCell2;
    private static PdfPCell fromToCell3;
    private static PdfPCell fromToCell4;
    private static PdfPCell fromToCell5;
    private static PdfPCell fromToCell6;
    private static PdfPCell amountCell2;



    public StockTurnOverReportGenerator(){

    }
    public static void generateTurnOverReport(int id){


        try {
            Document document = new Document(PageSize.A2.rotate());
            PdfWriter.getInstance(document, new FileOutputStream("report/report.pdf"));
            document.open();
            document.add(new Paragraph("Stock turnover report"));
            document.add(new Paragraph("\n"));

            PdfPTable receivedTable = new PdfPTable(1);
            receivedTable.addCell(new Paragraph("Received Blanks"));
            PdfPTable agentsSubAgentsTable = new PdfPTable(2);
            agentsSubAgentsTable.addCell(new Paragraph("Agents stock"));
            agentsSubAgentsTable.addCell(new Paragraph(" Sub Agents "));

            PdfPTable assignedUsedTable = new PdfPTable(1);
            assignedUsedTable.addCell(new Paragraph("Assigned/Used Blanks"));
            PdfPTable subAgentsTable = new PdfPTable(1);
            subAgentsTable.addCell(new Paragraph("Sub Agents"));

            PdfPTable finalTable = new PdfPTable(1);
            finalTable.addCell(new PdfPCell(new Paragraph("Final Amounts")));
            PdfPTable agentsSubAgentsTable2 = new PdfPTable(2);
            agentsSubAgentsTable2.addCell(new Paragraph("Agents Amount"));
            agentsSubAgentsTable2.addCell(new Paragraph("Sub Agents Amount"));


            PdfPTable fromToTable1 = new PdfPTable(2);
            fromToTable1.addCell(new Paragraph("From/To Blanks"));
            fromToTable1.addCell(new Paragraph("Amnt"));

            PdfPTable fromToTable2 = new PdfPTable(3);
            fromToTable2.addCell(new Paragraph("Code"));
            fromToTable2.addCell(new Paragraph("From/To Blanks"));
            fromToTable2.addCell(new Paragraph("Amnt"));

            PdfPTable fromToTable3 = new PdfPTable(5);
            fromToTable3.addCell(new Paragraph("Code"));
            fromToTable3.addCell(new Paragraph("Assigned From/To"));
            fromToTable3.addCell(new Paragraph("Amnt"));
            fromToTable3.addCell(new Paragraph("Used From/To"));
            fromToTable3.addCell(new Paragraph("Amnt"));


            PdfPTable fromToTable4 = new PdfPTable(2);
            fromToTable4.addCell(new Paragraph("From/To"));
            fromToTable4.addCell(new Paragraph("Amnt"));

            PdfPTable fromToTable5 = new PdfPTable(3);
            fromToTable5.addCell(new Paragraph("Code"));
            fromToTable5.addCell(new Paragraph("From/To"));
            fromToTable5.addCell(new Paragraph("Amnt"));






            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(Timestamp.valueOf(localDateTime.minusMonths(2)));

            endDate = Timestamp.valueOf(localDateTime);
            startDate = Timestamp.valueOf(localDateTime.minusMonths(1));
            con = db.getConnection();
            String sql = "SELECT * FROM stock"
                    + " WHERE (DateAdded BETWEEN ? AND ?) AND (Type=?)  ";
            executeQuery(sql);





            s = new StringBuilder();
            amountList = new ArrayList<>();
            codeList = new ArrayList<>();
            addToTable();


            System.out.println(s.toString());
            fromToCell1 = new PdfPCell(new Paragraph(s.toString()));
            fromToTable1.addCell(fromToCell1);
            fromToTable1.addCell(new Paragraph(formatString(amountList)));
            codeList.clear();
            amountList.clear();
            blankHashMap.clear();



            sql = "SELECT * FROM stock"
                    + " WHERE (DateAdded BETWEEN ? AND ?) AND (Type=?) AND (StaffID>0) ";
            executeQuery(sql);



            s = new StringBuilder();
            addToTable();
            fromToTable2.addCell(new Paragraph(formatString(codeList)));
            fromToCell2 = new PdfPCell(new Paragraph(s.toString()));
            fromToTable2.addCell(fromToCell2);
            fromToTable2.addCell(new Paragraph(formatString(amountList)));
            amountList.clear();
            codeList.clear();
            blankHashMap.clear();

            sql = "SELECT * FROM stock"
                    + " WHERE (DateAdded < ? AND DateAdded < ?) AND (Type=?) AND (StaffID>0) ";
            executeQuery(sql);

            s = new StringBuilder();
            addToTable();
            fromToTable3.addCell(new Paragraph(formatString(codeList)));
            fromToCell3 = new PdfPCell(new Paragraph(s.toString()) );
            fromToTable3.addCell(fromToCell3);
            fromToTable3.addCell(new Paragraph(formatString(amountList)));
            amountList.clear();
            codeList.clear();
            blankHashMap.clear();

            sql = "SELECT * FROM stock"
                    + " WHERE (DateUsed BETWEEN ? AND ?) AND (Type=?) ";
            executeQuery(sql);

            s = new StringBuilder();
            addToTable();
            fromToCell4 = new PdfPCell(new Paragraph(s.toString()));
            fromToTable3.addCell(fromToCell4);
            fromToTable3.addCell(new Paragraph(formatString(amountList)));
            amountList.clear();
            codeList.clear();
            blankHashMap.clear();

            sql = "SELECT * FROM stock"
                    + " WHERE ((Status='unassigned') OR (Status='Assigned')) AND (Type=?) ";



            for (int i = 0; i <typesOfBlanks.size() ; i++) {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1,typesOfBlanks.get(i));
                ResultSet rs = stm.executeQuery();

                while (rs.next()){
                    blankList.add(new Blank(rs.getLong(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getTimestamp(5)));
                }
                blankHashMap.put(typesOfBlanks.get(i), new ArrayList<>(blankList));
                blankList.clear();
            }


            s = new StringBuilder();
            addToTable();
            fromToCell5 = new PdfPCell(new Paragraph(s.toString()));
            fromToTable4.addCell(fromToCell5);
            fromToTable4.addCell(new Paragraph(formatString(amountList)));
            amountList.clear();
            codeList.clear();
            blankHashMap.clear();

            sql = "SELECT * FROM stock"
                    + " WHERE (DateAssigned BETWEEN ? AND ?) AND (Type=?) AND (StaffID>0)";
            executeQuery(sql);
            s = new StringBuilder();
            addToTable();
            fromToTable5.addCell(formatString(codeList));
            fromToCell6 = new PdfPCell(new Paragraph(s.toString()));
            fromToTable5.addCell(fromToCell6);
            fromToTable5.addCell(new Paragraph(formatString(amountList)));
            amountList.clear();
            codeList.clear();
            blankHashMap.clear();

            receivedTable.addCell(agentsSubAgentsTable);
            agentsSubAgentsTable.addCell(fromToTable1);
            agentsSubAgentsTable.addCell(fromToTable2);

            assignedUsedTable.addCell(subAgentsTable);
            subAgentsTable.addCell(fromToTable3);

            finalTable.addCell(agentsSubAgentsTable2);
            agentsSubAgentsTable2.addCell(fromToTable4);
            agentsSubAgentsTable2.addCell(fromToTable5);



            document.add(receivedTable);
            document.add(new Paragraph("\n\n"));
            document.add(assignedUsedTable);
            document.add(new Paragraph("\n\n"));
            document.add(finalTable);
            document.close();

            LocalDate localDate = LocalDate.now();
            Date dateAdded = Date.valueOf(localDate);

            File file = new File("report/report.pdf");
            FileInputStream fis = new FileInputStream(file);

            sql = "INSERT INTO report"
                    + " (dateAdded,Type,staffID,reportFile)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setDate(1,dateAdded);
            stm.setString(2,"tst");
            stm.setInt(3,id);
            stm.setBlob(4,fis);

            stm.execute();

        }catch (SQLException | IOException  | DocumentException ex){
            ex.printStackTrace();
        }
    }

    public static String formatString(List<String> list){
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s + "\n");
        }
        return sb.toString();
    }
    public static void addToTable(){
        it = blankHashMap.entrySet().iterator();
        Blank b;
        while(it.hasNext()){
            Map.Entry mapElement = (Map.Entry)it.next();
            blankList = (List<Blank>) mapElement.getValue();
            for (int i = 0; i <blankList.size() ; i++) {

            }
            Iterator it2 = blankList.iterator();
            List<Long> idList = new ArrayList<>();
            List<Blank> sameDateBlank = new ArrayList<>();
            Iterator it3;
            while (!blankList.isEmpty()) {

                b = (Blank)it2.next();
                Timestamp ts = b.getCreated_on();
                if (b.getCreated_on().equals(ts) && it2.hasNext()) {
                    sameDateBlank.add(b);
                    it2.remove();
                }
                while(it2.hasNext()){
                    b = (Blank)it2.next();
                    if (b.getCreated_on().equals(ts)){
                        sameDateBlank.add(b);
                        it2.remove();
                    }
                }
                it2 = blankList.iterator();

                it3 = sameDateBlank.iterator();

                while(it3.hasNext()){
                    b = (Blank) it3.next();
                    idList.add(b.getBlank_id());

                }

                int code = sameDateBlank.get(0).getStaffstaff_id();
                codeList.add(String.valueOf(code));
                Long min = Collections.min(idList);
                Long max = Collections.max(idList);

                s.append(min + " --- " + max + "\n");
                amountList.add(String.valueOf(max-min+1));

                sameDateBlank.clear();
                idList.clear();

            }

            blankList.clear();


        }
    }
    public static void executeQuery(String sql){
        try {
            for (int i = 0; i < typesOfBlanks.size(); i++) {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setTimestamp(1, startDate);
                stm.setTimestamp(2, endDate);
                stm.setInt(3, typesOfBlanks.get(i));

                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    blankList.add(new Blank(rs.getLong(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getString(4),
                            rs.getTimestamp(5)));
                }
                blankHashMap.put(typesOfBlanks.get(i), new ArrayList<>(blankList));
                blankList.clear();

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void viewReport(int reportID){
        try {
            Connection con = db.getConnection();
            String sql = "SELECT reportFile FROM report"
                    + " WHERE reportID=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,reportID);
            ResultSet rs = stm.executeQuery();
            File file = new File("report/report.pdf");
            FileOutputStream output = new FileOutputStream(file);

            while (rs.next()){
                InputStream input = rs.getBinaryStream("reportFile");

                byte[] buffer = new byte[1024];
                while(input.read(buffer) > 0){
                    output.write(buffer);
                }
            }
            Desktop.getDesktop().open(file);

        }catch (SQLException | IOException ex){
            ex.printStackTrace();
        }

    }




    public static void printHashMap (){
        Iterator it = blankHashMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry)it.next();
            blankList = (List<Blank>) mapElement.getValue();
            for (int i = 0; i <blankList.size() ; i++) {
                System.out.println(blankList.get(i).getBlank_id());
            }
        }
    }


    public static void main(String[] args) {
        StockTurnOverReportGenerator.generateTurnOverReport(1);
//        System.out.println(StockTurnOverReportGenerator.foo());
//        StockTurnOverReportGenerator.f();

    }
}
