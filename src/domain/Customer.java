package domain;
import java.util.*;


public class Customer {
    private int customerAccount_id;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String postalCode;
    private int telephone;
    private String email;
    private String customerType;
    private String discount;

    public Customer(int customerAccount_id, String name, Date dateOfBirth,
                    String address, String postalCode, int telephone, String email,
                    String customerType, String discount) {
        this.customerAccount_id = customerAccount_id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postalCode = postalCode;
        this.telephone = telephone;
        this.email = email;
        this.customerType = customerType;
        this.discount = discount;
    }

    public int getCustomerAccount_id() {
        return customerAccount_id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getDiscount() {
        return discount;
    }

    public Object[] rowArray(){
        return new Object[] {getCustomerAccount_id(),getName(),getDateOfBirth(),getAddress(), getPostalCode(),
                getTelephone(), getEmail(), getCustomerType(), getDiscount()};
    }
}
