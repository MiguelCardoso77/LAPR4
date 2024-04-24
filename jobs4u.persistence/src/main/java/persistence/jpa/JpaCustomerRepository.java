package persistence.jpa;

import core.domain.customer.TelephoneNumber;
import infrastructure.Application;
import core.domain.customer.Customer;
import core.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaCustomerRepository
        extends JpaAutoTxRepository<Customer, TelephoneNumber, TelephoneNumber>
        implements CustomerRepository {

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "mecanographicNumber");
    }

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "mecanographicNumber");
    }

    @Override
    public Optional<Customer> findByTelephoneNumber(final TelephoneNumber number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

    @Override
    public Iterable<Customer> findAllActive() {
        return match("e.systemUser.active = true");
    }
}
