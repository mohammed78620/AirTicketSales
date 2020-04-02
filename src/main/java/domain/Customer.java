package domain;
import java.util.*;


public class Customer {
    private int customerAccount_id;
    private String forename;
    private String surname;
    private Date dateOfBirth;
    private int telephone;
    private String email;
    private String customerType;
    private int discount;
    private String discountType;

    public Customer(int customerAccount_id, String forename, String surname, Date dateOfBirth,
                   int telephone, String email,
                    String customerType, int discount, String discountType) {
        this.customerAccount_id = customerAccount_id;
        this.forename = forename;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.email = email;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
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

    public int getDiscount() {
        return discount;
    }

    public String getDiscountType() { return discountType; }

    public Object[] rowArray(){
        return new Object[] {getCustomerAccount_id(),getForename(), getSurname(), getDateOfBirth(),
                getTelephone(), getEmail(), getCustomerType(), getDiscount(), getDiscountType()};
    }
}
