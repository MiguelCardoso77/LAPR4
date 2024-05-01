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
    /**
     * Constructor with a transactional context.
     *
     * @param autoTx the transactional context to use for database operations
     */

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "emailAddress");
    }

    /**
     * Constructor with a persistence unit name.
     *
     * @param puname the name of the persistence unit
     */
    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "emailAddress");
    }
    /**
     * Find a customer by their email address.
     *
     * @param emailAddress the email address of the customer to find
     * @return an {@link Optional} containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customer> findByEmailAddress(final EmailAddress emailAddress) {
        final Map<String, Object> params = new HashMap<>();
        params.put("email", emailAddress);
        return matchOne("e.emailAddress=:emailAddress", params);
    }
    /**
     * Retrieve all active customers.
     *
     * @return an iterable collection of active customers
     */
    @Override
    public Iterable<Customer> findAllActive() {
        return match("e.systemUser.active = true");
    }
}