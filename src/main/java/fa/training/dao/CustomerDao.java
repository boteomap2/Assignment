package fa.training.dao;

import fa.training.entity.Customer;
import java.util.List;

public interface CustomerDao {
    public boolean saveCustomer(Customer customer);
    public boolean updateCustomer(Customer customer);
    public boolean deleteCustomer(String id);
    public Customer getCustomer(String id);
    public List<Customer> getAllCustomer();
}
