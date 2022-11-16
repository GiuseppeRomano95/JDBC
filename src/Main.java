import jdbc.data.CustomerRepository;
import jdbc.data.DataException;
import jdbc.entities.Customer;
import org.postgresql.jdbc.PgConnection;

import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
//        CustomerRepository c = null;
//        try {
//            Optional<Customer> x = c.findById(12);
//            if(x.isPresent()){
//                Customer cust = x.get();
//            }
//        } catch (DataException e) {
//            throw new RuntimeException(e);
//        }
        String url = "jdbc:postgresql://localhost/student?user=postgresMaster&password=goPostgresGo";
        String allcustomers = "SELECT custid AS customer_id,companyname,address,city FROM customers";
        try(Connection c = DriverManager.getConnection(url);
            Statement st = c.createStatement();//Factory Method Pattern
            ResultSet rs = st.executeQuery(allcustomers);
            ){
            //Class.forName("org.postgresql.Driver");
            //NOOOOO!!!!!!! PgConnection c =(PgConnection) DriverManager.getConnection(url);
            System.out.println(c.getClass().getName());
            System.out.println(st.getClass().getName());
            System.out.println(rs.getClass().getName());
            while(rs.next()){
                int id = rs.getInt("customer_id");
                String cn = rs.getString("companyname");
                String add = rs.getString("address");
                String city = rs.getString("city");
                System.out.printf("id : %d, company name: %s, address: %s, city: %s%n",
                        id,cn,add,city);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        finally {
//            try {
//                if(rs != null){
//                   rs.close();
//                }
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
