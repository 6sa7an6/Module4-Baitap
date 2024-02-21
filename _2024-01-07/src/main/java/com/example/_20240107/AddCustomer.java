package com.example._20240107;

import service.CustomerServiceImpl;
import serviceimpl.ICustomerService;
import util.Format;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;


@WebServlet(name = "AddCustomer" , value = "/add-customer")
public class AddCustomer extends HttpServlet {
    public static ICustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Customer customer = null;
        if(id == null) {
            customer = new Customer();
        }else{
            customer = customerService.findById(id);
        }
        req.setAttribute("customer",customer);
        req.getRequestDispatcher("/view/addcustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("addcustomer");
        if(action != null){
            Date birthday;
            String name = req.getParameter("name");
            try {
               birthday = new Date(Format.spf.parse(req.getParameter("birthday")).getTime()) ;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String address = req.getParameter("address");
            Customer customer = new Customer(name,address,birthday);
            customerService.save(customer);
            List<Customer> list = customerService.getList();
            req.setAttribute("list",list);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
