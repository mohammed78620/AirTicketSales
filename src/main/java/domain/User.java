package domain;

public class User {
    private int staff_id;
    private String forname;
    private String surname;
    private String address;
    private String telephone;
    private String email;
    private String username;
    private String password;
    private String staffType;

    public User(int staff_id,String forname ,String surname , String address, String telephone, String email, String username, String password, String staffType) {
        this.staff_id = staff_id;
        this.forname = forname;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.staffType = staffType;
    }

    public int getStaff_id() {
        return staff_id;
    }
    public String getForname(){
        return forname;
    }
    public String getSurname(){
        return surname;
    }
    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStaffType() {
        return staffType;
    }
    public Object[] rowArray(){
        return new Object[] {getStaff_id(),
                getForname(),
                getSurname(),
                getAddress(),
                getTelephone(),
                getEmail(),
                getUsername(),
                getPassword(),
                getStaffType()};
    }
}
