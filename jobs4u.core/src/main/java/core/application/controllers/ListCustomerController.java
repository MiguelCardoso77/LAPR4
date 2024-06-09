package core.application.controllers;

import core.domain.customer.Customer;
import core.services.CustomerService;

/**
 * A controller class for listing customers.
 *
 * @author Diogo Ribeiro
 */
public class ListCustomerController {
    private final CustomerService customerService = new CustomerService();

    /**
     * Finds a customer with the given customer.
     *
     * @param customer the customer to search for
     * @return the found customer, or null if not found
     */
    public Customer findCustomer(Customer customer) {
        return customerService.findCustomer(customer);
    }

    /**
     * Retrieves all customers.
     *
     * @return an iterable of all customers
     */
    public Iterable<Customer> allCustomers() {
        return customerService.allCustomers();
    }
}
