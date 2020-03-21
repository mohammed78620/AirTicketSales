package domain;

public class Commission {
    private int commission_id;
    private Float commission_rate;
    private Boolean active;
    public Commission(int commission_id, Float commission_rate, Boolean active) {
        this.commission_id = commission_id;
        this.commission_rate = commission_rate;
        this.active = active;
    }

    public int getCommission_id() {
        return commission_id;
    }

    public Float getCommission_rate() {
        return commission_rate;
    }

    public Boolean getActive() {
        return active;
    }
    public Object[] rowArray(){
        return new Object[] {getCommission_id(),
                getCommission_rate(),
                getActive()};
    }
}
