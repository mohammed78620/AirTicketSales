package domain;
import java.util.*;


public class Discount {
    private int customerAccount_id;
    private String forename;
    private String surname;
    private String customerType;
    private int discount;
    private String discountType;

    public Discount(int customerAccount_id, String forename, String surname,
                    String customerType, int discount, String discountType) {
        this.customerAccount_id = customerAccount_id;
        this.forename = forename;
        this.surname = surname;
        this.customerType = customerType;
        this.discount = discount;
        this.discountType = discountType;
    }

    public int getCustomerAccount_id() {
        return customerAccount_id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() { return surname; }

    public String getCustomerType() {
        return customerType;
    }

    public int getDiscount() {
        return discount;
    }

    public String getDiscountType() {return discountType; }

    public Object[] rowArray(){
        return new Object[] {getCustomerAccount_id(),getForename(), getSurname(), getCustomerType(), getDiscount(), getDiscountType()};
    }
}