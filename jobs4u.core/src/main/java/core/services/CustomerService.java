package core.services;

import core.domain.company.Company;
import core.domain.customer.Customer;
import core.domain.customer.CustomerBuilder;
import core.persistence.PersistenceContext;
import core.repositories.CustomerRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * A service class for managing operations related to customers.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();
    /**
     * Registers a new customer with the specified system user, company, customer manager, and customer ID.
     *
     * @param systemUser      the system user associated with the customer
     * @param company         the company to which the customer belongs
     * @param customerManager the system user who manages the customer
     * @return the registered customer
     */
    @Transactional
    public Customer registerCustomer(SystemUser systemUser, Company company, SystemUser customerManager, EmailAddress emailAddress) {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.withUser(systemUser).withCompany(company).withCustomerManager(customerManager).withEmailAddress(emailAddress);
        Customer customer = customerBuilder.build();
        return customerRepository.save(customer);
    }
    /**
     * Retrieves all customers.
     *
     * @return an iterable of all customers
     */
    public Iterable<Customer> allCustomers(){
        return customerRepository.findAll();
    }
}