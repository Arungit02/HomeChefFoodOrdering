package com.homechef.service;

import com.homechef.dao.CustomerDAO;
import com.homechef.entity.Customer;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void registerCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
}