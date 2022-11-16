package jdbc.data;

import jdbc.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class DBCustomerRepository implements CustomerRepository {
    static final String URL = "jdbc:postgresql://localhost/student?user=postgresMaster&password=goPostgresGo";
    static final String ALL_CUSTOMERS = "SELECT custid AS customer_id,companyname,address,city FROM customers";
    static final String FIND_BY_ID = "SELECT custid AS customer_id,companyname,address,city FROM customers WHERE custid=?";
    static final String DELETE_BY_ID = "DELETE FROM customers WHERE custid=?";
    static final String UPDATE = "UPDATE customers SET companyname = ?, address = ?, city = ? WHERE custid = ?";
    static final String INSERT = "INSERT INTO customers(companyname,address,city) VALUES(?,?,?)";

    @Override
    public Iterable<Customer> getAll() throws DataException {
        try (Connection c = DriverManager.getConnection(URL);
             Statement st = c.createStatement();//Factory Method Pattern
             ResultSet rs = st.executeQuery(ALL_CUSTOMERS);
        ) {
            Collection<Customer> custs = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("customer_id");
                String cn = rs.getString("companyname");
                String add = rs.getString("address");
                String city = rs.getString("city");
                Customer customer = new Customer(id, cn, add, city);
                custs.add(customer);
            }
            return custs;
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Customer> findById(int id) throws DataException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement st = c.prepareStatement(FIND_BY_ID);//Factory Method Pattern
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int custId = rs.getInt("customer_id");
                    String cn = rs.getString("companyname");
                    String add = rs.getString("address");
                    String city = rs.getString("city");
                    Customer customer = new Customer(id, cn, add, city);
                    return Optional.of(customer);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(int id) throws DataException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement st = c.prepareStatement(DELETE_BY_ID);
        ) {
            st.setInt(1, id);
            int numberRows = st.executeUpdate();
            return numberRows == 1;
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Customer customer) throws DataException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement st = c.prepareStatement(UPDATE);) {
            st.setString(1, customer.getCompanyname());
            st.setString(2, customer.getAddress());
            st.setString(3, customer.getCity());
            st.setInt(4, customer.getCustid());
            int numberRows = st.executeUpdate();
            return numberRows == 1;
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    @Override
    public void insert(Customer customer) throws DataException {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement st = c.prepareStatement(INSERT);) {
            st.setString(1, customer.getCompanyname());
            st.setString(2, customer.getAddress());
            st.setString(3, customer.getCity());
            st.execute();
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }
}
