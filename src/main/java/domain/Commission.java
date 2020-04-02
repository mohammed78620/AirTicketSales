package domain;

public class Commission {
    private int commission_id;
    private Float commission_rate;
    private int type;
    public Commission(int commission_id, Float commission_rate, int type) {
        this.commission_id = commission_id;
        this.commission_rate = commission_rate;
        this.type = type;
    }

    public int getCommission_id() {
        return commission_id;
    }

    public Float getCommission_rate() {
        return commission_rate;
    }

    public int getType() {
        return type;
    }
    public Object[] rowArray(){
        return new Object[] {getCommission_id(),
                getCommission_rate(),
                getType()};
    }
}
