package persistence.jpa;

import core.domain.application.Application;
import core.repositories.ApplicationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * @author 1220812@isep.ipp.pt
 */

public class JpaApplicationRepository extends JpaAutoTxRepository<Application, Integer, Integer> implements ApplicationRepository {
    /**
     * Constructor with a transactional context.
     *
     * @param autoTx the transactional context to use for database operations
     */
    public JpaApplicationRepository(final TransactionalContext autoTx) {super(autoTx,"idApplication");}
    /**
     * Constructor with a persistence unit name.
     *
     * @param puname the name of the persistence unit
     */
    public JpaApplicationRepository(final String puname) {super(puname, "idApplication"); }
    /**
     * Retrieve all applications.
     *
     * @return an iterable collection of all applications
     */
    @Override
    public Iterable<Application> allApplications() {
        return findAll();
    }
}
