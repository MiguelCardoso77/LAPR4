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
 * JPA implementation of the CustomerRepository interface.
 * This repository provides access to Customer entities using JPA for persistence.
 *
 * @author Diogo Ribeiro
 */
class JpaCustomerRepository extends JpaAutoTxRepository<Customer, EmailAddress, EmailAddress> implements CustomerRepository {

    /**
     * Constructs a new JpaCustomerRepository with the given transactional context.
     *
     * @param autoTx the transactional context to use for database operations.
     */
    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "emailAddress");
    }

    /**
     * Constructs a new JpaCustomerRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "emailAddress");
    }

    /**
     * Finds a customer by their email address.
     *
     * @param emailAddress the email address of the customer to find.
     * @return an {@link Optional} containing the customer if found, or empty if not found.
     */
    @Override
    public Optional<Customer> findByEmailAddress(final EmailAddress emailAddress) {
        final Map<String, Object> params = new HashMap<>();
        params.put("emailAddress", emailAddress);
        return matchOne("e.emailAddress=:emailAddress", params);
    }

    /**
     * Retrieves all active customers.
     *
     * @return an iterable collection of active customers.
     */
    @Override
    public Iterable<Customer> findAllActive() {
        return match("e.systemUser.active = true");
    }

}