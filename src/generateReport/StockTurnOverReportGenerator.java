package generateReport;

import database.DatabaseHelper;
import domain.Blank;

import javax.annotation.processing.FilerException;
import javax.swing.text.Document;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockTurnOverReportGenerator {
    private static DatabaseHelper db = new DatabaseHelper();
    private static Connection con;
    private static FileWriter fileWriter;
    private static List<Blank> blankList = new ArrayList<>();

    public StockTurnOverReportGenerator(){

    }
    public static void generateTurnOverReport(){
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println(Timestamp.valueOf(localDateTime.minusMonths(2)));

            Timestamp endDate = Timestamp.valueOf(localDateTime);
            Timestamp startDate = Timestamp.valueOf(localDateTime.minusMonths(1));
            con = db.getConnection();
            String sql = "SELECT * FROM stock"
                    + " WHERE (DateAdded BETWEEN ? AND ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setTimestamp(1,startDate);
            stm.setTimestamp(2,endDate);

            ResultSet rs = stm.executeQuery();
            blankList = new ArrayList<>();
            Blank b;
            while (rs.next()) {
                blankList.add(new Blank(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getTimestamp(5)));
            }


            File file = new File("report/report.txt");
            fileWriter = new FileWriter(file);

            fileWriter.write("Received Blanks\n\n");
            fileWriter.write("Agent's stock\n\n");
            fileWriter.write("From/To Blanks NBRS\n");
            writeToFile();

            Iterator it = blankList.iterator();
            while(it.hasNext()) {
                b = (Blank)it.next();
                if(b.getStaffstaff_id() == 0 ){
                    it.remove();
                }
            }
            for (int i = 0; i <blankList.size() ; i++) {
                b =  blankList.get(i);
                System.out.println(b.row());
            }
            fileWriter.write("Sub Agents\n");
            fileWriter.write("From/To Blanks NBRS\n");
            writeToFile();

            fileWriter.write("Assigned/Used Blanks\n\n");
            fileWriter.write("Sub Agents\n\n");
            fileWriter.write("From/To Blanks NBRS\n");






            fileWriter.close();
        }catch (IOException | SQLException ex){
            ex.printStackTrace();
        }
    }
    public static void writeToFile(){
        Blank b;
        try {
            for (int i = 0; i < blankList.size(); i++) {
                b = blankList.get(i);
                fileWriter.write(b.blankNumber()
                        + "\n\n");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static String foo(){
        LocalDate date = LocalDate.now().minusMonths(1).minusDays(1);
        return date.toString();
    }

    public static void main(String[] args) {
        StockTurnOverReportGenerator.generateTurnOverReport();
//        System.out.println(StockTurnOverReportGenerator.foo());


    }
}
