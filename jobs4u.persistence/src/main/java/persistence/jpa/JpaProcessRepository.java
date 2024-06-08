package persistence.jpa;

import core.domain.process.Process;
import core.repositories.ProcessRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the ProcessRepository interface.
 * This repository provides access to Process entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaProcessRepository extends JpaAutoTxRepository<Process, Integer, Integer> implements ProcessRepository {

    /**
     * Constructs a new JpaProcessRepository with the given persistence unit name.
     *
     * @param persistenceUnitName the name of the persistence unit.
     */
    public JpaProcessRepository(String persistenceUnitName) {
        super(persistenceUnitName, "idProcess");
    }

    /**
     * Constructs a new JpaProcessRepository with the given transactional context.
     *
     * @param tx the transactional context.
     */
    public JpaProcessRepository(TransactionalContext tx) {
        super(tx, "idProcess");
    }

    /**
     * Retrieves all Process entities.
     *
     * @return an iterable collection of all Process entities.
     */
    @Override
    public Iterable<Process> allProcesses() {
        return findAll();
    }
}
