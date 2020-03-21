package domain;
import java.util.*;


public class Discount {
    private int customerAccount_id;
    private String name;
    private String customerType;
    private String discount;

    public Discount(int customerAccount_id, String name,
                    String customerType, String discount) {
        this.customerAccount_id = customerAccount_id;
        this.name = name;
        this.customerType = customerType;
        this.discount = discount;
    }

    public int getCustomerAccount_id() {
        return customerAccount_id;
    }

    public String getName() {
        return name;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getDiscount() {
        return discount;
    }

    public Object[] rowArray(){
        return new Object[] {getCustomerAccount_id(),getName(),getCustomerType(), getDiscount()};
    }
}