package domain;

public class Blank {
    private int blank_id;
    private int Staffstaff_id;
    private int blankType;
    private String flightType;
    public Blank(int blank_id, int Staffstaff_id, int blankType, String flightType){
        this.blank_id = blank_id;
        this.Staffstaff_id = Staffstaff_id;
        this.blankType = blankType;
        this.flightType = flightType;
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

    public String getFlightType() {
        return flightType;
    }
    public String row(){
        return "blank id: " +getBlank_id()
                + " staff id: " + getStaffstaff_id()
                + " type: " + getBlankType()
                + " flight type: " + getFlightType();
    }
    public Object[] rowArray(){
        return new Object[] {getBlank_id(),getStaffstaff_id(),getBlankType(),getFlightType()};
    }
}
