package service;

import com.example._20240107.Customer;
import serviceimpl.ICustomerService;
import util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    @Override
    public void save(Customer customer) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement call;
            try {
        if(customer.getId() == null) {
            call = conn.prepareCall("insert into customer(name,birthday,address) values (?,?,?)");
            call.setString(1, customer.getName());
            call.setDate(2, customer.getBirthday());
            call.setString(3, customer.getAddress());
            call.executeUpdate();
        }else {
            call = conn.prepareCall("UPDATE customer set name = ? , birthday = ? , address = ? where id = ?");
            call.setString(1,customer.getName());
            call.setDate(2, customer.getBirthday());
            call.setString(3, customer.getAddress());
            call.setString(4,customer.getId());
            call.executeUpdate();
        }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public Customer findById(String id) {
        Connection conn = ConnectDB.getConnection();
        Customer customer = new Customer();
        try {
            CallableStatement call = conn.prepareCall("SELECT * from customer where id = ?");
            call.setString(1,id);
            ResultSet rs = call.executeQuery();
            if(rs.next()) {
                customer.setId(id);
                customer.setName(rs.getString("name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public void delete(String id) {
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement call = conn.prepareCall("DELETE FROM customer where id = ?");
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getList() {
        Connection conn = ConnectDB.getConnection();
        List<Customer> list = new ArrayList<>();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM customer");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
