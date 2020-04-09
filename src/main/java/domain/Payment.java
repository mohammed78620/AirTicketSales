package main.java.domain;

import java.sql.Timestamp;

public class Payment {
    private int PaymentID;
    private String Type;
    private Float amount;
    private Float amountAfterTax;
    private int CommissionID;
    private int customerID;
    private int RateID;
    private int StaffID;
    private Timestamp PaymentDate;
    private String TicketType;
    private Long blankID;

    public Payment(int paymentID, String type, Float amount, Float amountAfterTax, int commissionID, int customerID, int rateID, int staffID, Timestamp paymentDate, String ticketType, Long blankID) {
        this.PaymentID = paymentID;
        this.Type = type;
        this.amount = amount;
        this.amountAfterTax = amountAfterTax;
        this.CommissionID = commissionID;
        this.customerID = customerID;
        this.RateID = rateID;
        this.StaffID = staffID;
        this.PaymentDate = paymentDate;
        this.TicketType = ticketType;
        this.blankID = blankID;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public String getType() {
        return Type;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getAmountAfterTax() {
        return amountAfterTax;
    }

    public int getCommissionID() {
        return CommissionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRateID() {
        return RateID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public Timestamp getPaymentDate() {
        return PaymentDate;
    }

    public String getTicketType() {
        return TicketType;
    }
    public Long getBlankID(){
        return blankID;
    }
    public String print(){
        return getPaymentID()
                + getType()
                + getAmount()
                + getAmountAfterTax()
                + getCommissionID()
                + getCustomerID()
                + getRateID()
                + getStaffID()
                + getPaymentID()
                + getTicketType()
                +getBlankID();
    }
    public Object[] rowArray(){
        return new Object[] {getPaymentID(),
        getType(),
        getAmount(),
        getAmountAfterTax(),
        getCommissionID(),
        getCustomerID(),
        getRateID(),
        getStaffID(),
        getPaymentDate(),
        getTicketType(),
        getBlankID()};
    }
}
