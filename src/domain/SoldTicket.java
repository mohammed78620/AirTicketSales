package domain;
import java.sql.Timestamp;

public class SoldTicket {
    private int salesID;
    private Timestamp salesDate;
    private int tax;
    private int subTotal;
    private int grandTotal;
    private int salesNote;
    private int customerID;
    private int paymentID;
    private int commissionID;

    public SoldTicket(int salesID, Timestamp salesDate, int tax, int subTotal, int grandTotal, int salesNote, int customerID, int paymentID, int commissionID) {
        this.salesID = salesID;
        this.salesDate = salesDate;
        this.tax = tax;
        this.subTotal = subTotal;
        this.grandTotal = grandTotal;
        this.salesNote = salesNote;
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.commissionID = commissionID;
    }

    public int getSalesID() {
        return salesID;
    }

    public Timestamp getSalesDate() {
        return salesDate;
    }

    public int getTax() {
        return tax;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public int getSalesNote() {
        return salesNote;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getCommissionID() {
        return commissionID;
    }

    public Object[] rowArray(){
        return new Object[]{getSalesID(), getSalesDate(), getTax(), getSubTotal(), getGrandTotal(), getSalesNote(), getCustomerID(), getPaymentID(), getCommissionID()};
    }
}

