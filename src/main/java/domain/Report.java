package main.java.domain;

import java.sql.Date;

public class Report {
    private int reportID;
    private Date dateAdded;
    private String Type;
    private int staffID;

    public Report(int reportID, Date dateAdded, String type, int staffID) {
        this.reportID = reportID;
        this.dateAdded = dateAdded;
        Type = type;
        this.staffID = staffID;
    }

    public int getReportID() {
        return reportID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getType() {
        return Type;
    }

    public int getStaffID() {
        return staffID;
    }
    public Object[] rowArray(){
        return new Object[] {getReportID(),getDateAdded(),getType(),getStaffID()};
    }
}
