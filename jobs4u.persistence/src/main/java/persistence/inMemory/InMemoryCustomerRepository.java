package persistence.inMemory;

import core.domain.customer.Customer;
import core.repositories.CustomerRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, EmailAddress> implements CustomerRepository {

    /**
     * Static block to initialize the in-memory data store.
     */
    static {
        InMemoryInitializer.init();
    }
    /**
     * Find a customer by their email address.
     *
     * @param emailAddress the email address of the customer to find
     * @return an {@link Optional} containing the customer if found, or empty if not found
     */

    @Override
    public Optional<Customer> findByEmailAddress(final EmailAddress emailAddress) {
        return Optional.of(data().get(emailAddress));
    }

    /**
     * Retrieve all active customers.
     *
     * @return an iterable collection of active customers
     */

    @Override
    public Iterable<Customer> findAllActive() {
        return match(e -> e.user().isActive());
    }
}