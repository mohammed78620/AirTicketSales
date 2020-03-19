package domain;

public class User {
    private int staff_id;
    private String name;
    private String address;
    private int telephone;
    private String email;
    private String username;
    private String password;
    private String staffType;

    public User(int staff_id, String name, String address, int telephone, String email, String username, String password, String staffType) {
        this.staff_id = staff_id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getTelephone() {
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
                getName(),
                getAddress(),
                getTelephone(),
                getEmail(),
                getUsername(),
                getPassword(),
                getStaffType()};
    }
}
