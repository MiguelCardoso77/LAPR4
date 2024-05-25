package core.application;

import core.domain.customer.Customer;
import core.services.CustomerService;

/**
 * A controller class for listing customers.
 *
 * @author 1220812@isep.ipp.pt
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
