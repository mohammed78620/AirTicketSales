package domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Blank {
    private int blank_id;
    private int Staffstaff_id;
    private int blankType;
    private String status;
    private Timestamp created_on;
    public Blank(int blank_id, int Staffstaff_id, int blankType, String  status,Timestamp created_on){
        this.blank_id = blank_id;
        this.Staffstaff_id = Staffstaff_id;
        this.blankType = blankType;
        this.status = status;
        this.created_on = created_on;
    }
    public int getBlank_id(){
        return blank_id;
    }

    public int getStaffstaff_id() {
        return Staffstaff_id;
    }

    public int getBlankType() {
        return blankType;
    }

    public String getStatus(){
        return status;
    }
    public Timestamp getCreated_on(){
        return created_on;
    }
    public String blankNumber(){
        return String.valueOf(getBlankType()) + String.valueOf(getBlank_id());
    }

    public String row(){
        return "blank id: " +getBlank_id()
                + " staff id: " + getStaffstaff_id()
                + " type: " + getBlankType();
    }
    public Object[] rowArray(){
        return new Object[] {String.valueOf(blankType)+String.valueOf(getBlank_id()),getStaffstaff_id(),getBlankType(),getStatus(),getCreated_on()};
    }
}
