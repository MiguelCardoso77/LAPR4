package core.services;

import core.domain.customer.Company;
import core.domain.customer.Customer;
import core.domain.customer.CustomerBuilder;
import core.domain.customer.TelephoneNumber;
import core.repositories.CustomerRepository;
import eapli.framework.infrastructure.authz.domain.model.*;
import jakarta.transaction.Transactional;

/**
 * Service class for registering new customers.
 * This class facilitates the registration of new customers by providing a method to register a customer
 * with the provided system user, telephone number, and company.
 * It uses a CustomerRepository to persist the newly registered customer.
 */

public class RegisterCustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Constructs a RegisterCustomerService with the given CustomerRepository.
     *
     * @param customerRepository the repository for managing customer data
     */
    public RegisterCustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    /**
     * Registers a new customer with the provided system user, telephone number, and company.
     * This method creates a new customer using the provided parameters and saves it using the CustomerRepository.
     *
     * @param systemUser      the system user associated with the customer
     * @param telephoneNumber the telephone number of the customer
     * @param company         the company associated with the customer
     * @return the newly registered customer
     */
    @Transactional
    public Customer registerNewCustomer(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Company company) {
        Customer customer = createCustomer(systemUser, telephoneNumber, company);

        return customerRepository.save(customer);
    }
    /**
     * Creates a new customer with the provided system user, telephone number, and company.
     *
     * @param systemUser      the system user associated with the customer
     * @param telephoneNumber the telephone number of the customer
     * @param company         the company associated with the customer
     * @return the newly created customer
     */
    private Customer createCustomer(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Company company) {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.withAll(systemUser, telephoneNumber, company);
        Customer newCustomer = customerBuilder.build();
        return (Customer) this.customerRepository.save(newCustomer);
    }

}
