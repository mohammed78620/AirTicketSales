package domain;

import java.util.Date;

public class Stock {
    private int blank_id;
    private int blankType;
    private String status;
    private Date created_on;
    public Stock(int blank_id, int blankType, String status,Date created_on){
        this.blank_id = blank_id;
        this.blankType = blankType;
        this.status = status;
        this.created_on = created_on;
    }
    public int getBlank_id(){
        return blank_id;
    }

    public int getBlankType() {
        return blankType;
    }

    public String getStatus(){
        return status;
    }
    public Date getCreated_on(){
        return created_on;
    }

    public String row(){
        return "blank id: " +getBlank_id()
                + " type: " + getBlankType();
    }
    public Object[] rowArray(){
        return new Object[] {getBlank_id(),getBlankType(),getStatus(),getCreated_on()};
    }
}
