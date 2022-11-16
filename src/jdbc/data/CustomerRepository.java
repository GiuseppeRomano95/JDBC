package jdbc.data;

import jdbc.entities.Customer;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerRepository {
    Iterable<Customer> getAll() throws DataException;
    Optional<Customer> findById(int id) throws DataException;
    boolean delete(int id) throws DataException;
    boolean update(Customer c) throws DataException;

    void insert(Customer c) throws DataException;
}
