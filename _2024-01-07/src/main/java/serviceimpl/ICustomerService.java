package serviceimpl;

import com.example._20240107.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getList();
    void save(Customer customer);
    Customer findById(String id);
    void delete(String id);
}
