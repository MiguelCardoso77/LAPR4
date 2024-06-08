package persistence.jpa;

import core.domain.application.Application;
import core.repositories.ApplicationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the ApplicationRepository interface.
 * This repository provides access to Application entities using JPA for persistence.
 *
 * @author Diogo Ribeiro
 */
public class JpaApplicationRepository extends JpaAutoTxRepository<Application, Integer, Integer> implements ApplicationRepository {

    /**
     * Constructs a new JpaApplicationRepository with the given transactional context.
     *
     * @param autoTx the transactional context to use for database operations.
     */
    public JpaApplicationRepository(final TransactionalContext autoTx) {
        super(autoTx, "idApplication");
    }

    /**
     * Constructs a new JpaApplicationRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaApplicationRepository(final String puname) {
        super(puname, "idApplication");
    }

    /**
     * Retrieves all Application entities.
     *
     * @return an iterable collection of all Application entities.
     */
    @Override
    public Iterable<Application> allApplications() {
        return findAll();
    }

}
