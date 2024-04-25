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

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Optional<Customer> findByEmailAddress(final EmailAddress emailAddress) {
        return Optional.of(data().get(emailAddress));
    }

    @Override
    public Iterable<Customer> findAllActive() {
        return match(e -> e.user().isActive());
    }
}
