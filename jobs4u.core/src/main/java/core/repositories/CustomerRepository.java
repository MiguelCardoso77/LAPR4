package core.repositories;

import core.domain.customer.Customer;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.Optional;

/**
 * A repository for customers.
 *
 * @author Diogo Ribeiro
 */
public interface CustomerRepository extends DomainRepository<EmailAddress, Customer> {

    /**
     * Find a customer by their email address.
     *
     * @param emailAddress the email address of the customer to find
     * @return an {@link Optional} containing the customer if found, or empty if not found
     */
    Optional<Customer> findByEmailAddress(final EmailAddress emailAddress);

    /**
     * Retrieve all active customers.
     *
     * @return an iterable collection of active customers
     */
    Iterable<Customer> findAllActive();
}
