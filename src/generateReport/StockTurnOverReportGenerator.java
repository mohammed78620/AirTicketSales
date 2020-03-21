package generateReport;

import database.DatabaseHelper;

import javax.swing.text.Document;
import java.sql.Connection;

public class StockTurnOverReportGenerator {
    DatabaseHelper db = new DatabaseHelper();
    public StockTurnOverReportGenerator(){

    }
    public void generateTurnOverReport(){
        Connection con = db.getConnection();

    }
}
