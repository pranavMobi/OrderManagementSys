package org.example.oms.service;


import org.example.oms.dao.CustomerRepository;
import org.example.oms.model.Customer;

import java.util.List;

public class CustomerService {
        private final CustomerRepository customerRepo;

        public CustomerService(CustomerRepository customerDAO) {
            this.customerRepo = customerDAO;
        }

        public Customer getCustomerById(int customerId) {
            return customerRepo.getCustomerById(customerId);
        }

        public List<Customer> getAllCustomers() {
            return customerRepo.getAllCustomers();
        }

        public void addCustomer(Customer customer) {
            customerRepo.addCustomer(customer);
        }

        public void updateCustomer(Customer customer) {
            customerRepo.updateCustomer(customer);
        }


}
