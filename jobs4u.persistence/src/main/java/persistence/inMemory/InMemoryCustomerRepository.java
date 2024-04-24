package persistence.inMemory;

import core.domain.customer.Customer;
import core.domain.customer.TelephoneNumber;
import core.repositories.CustomerRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, TelephoneNumber> implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Optional<Customer> findByTelephoneNumber(final TelephoneNumber number) {
        return Optional.of(data().get(number));
    }

    @Override
    public Iterable<Customer> findAllActive() {
        return match(e -> e.user().isActive());
    }
}
