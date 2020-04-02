package domain;

public class Rate {
    private int exchangeRate_id;
    private String currency;
    private Float exchangeRate;

    public Rate(int exchangeRate_id, String currency, Float exchangeRate) {
        this.exchangeRate_id = exchangeRate_id;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public int getExchangeRate_id() {
        return exchangeRate_id;
    }

    public String getCurrency() {
        return currency;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }
    public Object[] rowArray(){
        return new Object[] {getExchangeRate_id(),
        getCurrency(),
        getExchangeRate()};
    }
}
