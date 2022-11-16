package jdbc.entities;

public class Customer {
    private int custid;
    private String companyname;
    private String address;
    private String city;

    public Customer(int custid, String companyname, String address, String city) {
        this.custid = custid;
        this.companyname = companyname;
        this.address = address;
        this.city = city;
    }

    public int getCustid() {
        return custid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
