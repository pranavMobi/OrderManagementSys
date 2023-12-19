package org.oms.controller;
import org.oms.customer.Customer;
import org.oms.customer.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService customerService = new CustomerService();


    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public Customer getCustomerById(int customerId) {
        return customerService.getCustomerById(customerId);
    }


    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }


    public void updateCustomer(int customerId, Customer updatedCustomer) {
        customerService.updateCustomer(customerId, updatedCustomer);
    }

    public void deleteCustomer(int customerId) {
        customerService.deleteCustomer(customerId);
    }
}

