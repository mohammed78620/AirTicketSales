package domain;

import java.sql.Date;

public class Backup {
    private int idbackup;
    private Date dateAdded;

    public Backup(int idbackup, Date dateAdded) {
        this.idbackup = idbackup;
        this.dateAdded = dateAdded;
    }

    public int getIdbackup() {
        return idbackup;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
    public Object[] rowArray(){
        return new Object[] {getIdbackup(),getDateAdded()};
    }

}
