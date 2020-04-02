package database;

import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.*;

public class Database {
    static String name = "airticketsales";
    static String user = "akmal";
    static String password = "]WCgDKEN69Wf>zE.";
    private static Connection con;
    private static DatabaseHelper db = new DatabaseHelper();
    public Database(){

    }
    public static void backupDatabase(){
        try {
            String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump -u akmal -p\"]WCgDKEN69Wf>zE.\" airticketsales card_details " +
                    "commission customer exchange_rate payment sale sold_tickets staff stock";

            Process runTimeProcess = Runtime.getRuntime().exec(executeCmd);

            BufferedReader reader = new BufferedReader(new InputStreamReader(runTimeProcess.getInputStream()));

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("backup/backup.sql")));
            String line = reader.readLine();
            while(line != null){
                writer.write(line);
                writer.newLine();
                line  = reader.readLine();
            }
            writer.close();

            con = db.getConnection();

            String sql = "INSERT INTO backups "
                    + " (backupFile,dateAdded) "
                    + "VALUES (?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            File file = new File("backup/backup.sql");
            FileReader input = new FileReader(file);

            Long millis = System.currentTimeMillis();
            Date date = new Date(millis);

            stm.setCharacterStream(1,input);
            stm.setDate(2,date);

            stm.execute();




        }catch (IOException | SQLException ex){
            ex.getMessage();
            ex.printStackTrace();

        }

    }
    public static void restoreDatabase(int index){
        try{
          con = db.getConnection();

          String sql = "SELECT backupFile FROM backups WHERE idbackup=?";

          PreparedStatement stm = con.prepareStatement(sql);

          stm.setInt(1,index);

          ResultSet rs = stm.executeQuery();

          File file = new File("backup/backup.sql");
          FileOutputStream output = new FileOutputStream(file);

          if(rs.next()){
              Reader input = rs.getCharacterStream("backupFile");

              int theChar;
              while((theChar = input.read()) > 0){
                  output.write(theChar);
              }
          }
          output.close();

          String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql -u akmal -p\"]WCgDKEN69Wf>zE.\" airticketsales -e \"source  C:\\IN2018TeamProject\\AirTicketSales\\backup\\backup.sql\"";

          Process runTimeProcess = Runtime.getRuntime().exec(executeCmd);

          BufferedReader reader = new BufferedReader(new InputStreamReader(runTimeProcess.getInputStream()));

          String line = reader.readLine();

          while(line != null){
              System.out.println(line);
              line = reader.readLine();
          }



        }catch (IOException | SQLException ex){
            ex.getMessage();
            ex.printStackTrace();
        }
    }

}
