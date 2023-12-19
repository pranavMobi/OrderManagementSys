package org.oms.resource;

import org.oms.customer.Customer;
import org.oms.customer.CustomerService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    private final CustomerService customerService = new CustomerService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customerId") int customerId) {
        return customerService.getCustomerById(customerId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }

    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCustomer(@PathParam("customerId") int customerId, Customer updatedCustomer) {
        customerService.updateCustomer(customerId, updatedCustomer);
    }

    @DELETE
    @Path("/{customerId}")
    public void deleteCustomer(@PathParam("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }
}
