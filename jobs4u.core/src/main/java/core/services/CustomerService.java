package core.services;

import core.application.controllers.AddUserController;
import core.domain.company.Company;
import core.domain.customer.Customer;
import core.domain.customer.CustomerBuilder;
import core.persistence.PersistenceContext;
import core.repositories.CustomerRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A service class for managing operations related to customers.
 *
 * @author 1220812@isep.ipp.pt
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();
    private final AddUserController addUserController = new AddUserController();
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

    /**
     * Retrieves a customer from the customer repository based on the provided customer ID.
     *
     * @param customerId The customer ID to search for.
     * @return The customer object corresponding to the provided customer ID if found,
     */
    public Customer findCustomer(Customer customerId) {
        Iterable<Customer> customers = customerRepository.findAll();
        for(Customer customer : customers){
            if(customer.identity().equals(customerId.identity()) ){
                return customer;
            }
        }
        return null;
    }

    /**
     * Finds the customer associated with a given system user.
     *
     * @param systemUser The system user whose associated customer is to be found.
     * @return The customer associated with the specified system user, or null if no such customer is found.
     */
    public Customer findCustomerByUser(SystemUser systemUser){
        List<SystemUser> allSystemUsers = addUserController.allUsers();
        for(SystemUser user : allSystemUsers){
            if(user.identity().equals(systemUser.identity())){
                Optional<Customer> customerOptional = customerRepository.findByEmailAddress(systemUser.email());
                return customerOptional.orElse(null);
            }
        }
        return null;
    }

    /**
     * Finds the customer associated with a given email.
     *
     * @param email The email whose associated customer is to be found.
     * @return The customer associated with the specified email, or null if no such customer is found.
     */
    public Customer findCustomerByEmail(String email){
        EmailAddress emailAddress = EmailAddress.valueOf(email);

        Iterable<Customer> allCustomers = customerRepository.findAll();
        if(allCustomers.iterator().hasNext()){
            for(Customer customer : allCustomers){
                if(customer.identity().equals(emailAddress)){
                    return customer;
                }
            }
        }
        return null;
    }
}