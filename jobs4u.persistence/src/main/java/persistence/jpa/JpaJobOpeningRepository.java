package persistence.jpa;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.repositories.JobOpeningRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the JobOpeningRepository interface.
 * This repository provides access to JobOpening entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaJobOpeningRepository extends JpaAutoTxRepository<JobOpening, JobReference, JobReference> implements JobOpeningRepository {

    /**
     * Constructs a new JpaJobOpeningRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     */
    public JpaJobOpeningRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobReference");
    }

    /**
     * Constructs a new JpaJobOpeningRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaJobOpeningRepository(final String puname) {
        super(puname, "jobReference");
    }

    /**
     * Retrieves all JobOpening entities.
     *
     * @return an iterable collection of all JobOpening entities.
     */
    @Override
    public Iterable<JobOpening> allJobOpenings() {
        return findAll();
    }

}
