package persistence.jpa;

import eapli.framework.general.domain.model.EmailAddress;
import infrastructure.Application;
import core.domain.customer.Customer;
import core.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCustomerRepository extends JpaAutoTxRepository<Customer, EmailAddress, EmailAddress> implements CustomerRepository {

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "emailAddress");
    }

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "emailAddress");
    }

    @Override
    public Optional<Customer> findByEmailAddress(final EmailAddress emailAddress) {
        final Map<String, Object> params = new HashMap<>();
        params.put("email", emailAddress);
        return matchOne("e.emailAddress=:emailAddress", params);
    }

    @Override
    public Iterable<Customer> findAllActive() {
        return match("e.systemUser.active = true");
    }
}
