package org.oms.customer;


import org.oms.customer.CustomerRepository;
import org.oms.customer.Customer;

import java.util.List;

public class CustomerService {
        private final CustomerRepository customerRepo;

        public CustomerService() {
            this.customerRepo = new CustomerRepository();
        }

        public  Customer getCustomerById(int customerId) {
            return customerRepo.getCustomerById(customerId);
        }

        public  List<Customer> getAllCustomers() {
            return customerRepo.getAllCustomers();
        }

        public  void addCustomer(Customer customer) {
            customerRepo.addCustomer(customer);
        }

        public  void updateCustomer(int CustomerId,Customer customer) {
            customerRepo.updateCustomer(customer);
        }


    public void deleteCustomer(int customerId) {customerRepo.deleteCustomer(customerId); }
}
