package persistence.jpa;

import core.domain.interview.JobInterview;
import core.repositories.JobInterviewRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the JobInterviewRepository interface.
 * This repository provides access to JobInterview entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaJobInterviewRepository extends JpaAutoTxRepository<JobInterview, Integer, Integer> implements JobInterviewRepository {

    /**
     * Constructs a new JpaJobInterviewRepository with the given persistence unit name.
     *
     * @param persistenceUnitName the name of the persistence unit.
     */
    public JpaJobInterviewRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    /**
     * Constructs a new JpaJobInterviewRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     */
    public JpaJobInterviewRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Retrieves all JobInterview entities.
     *
     * @return an iterable collection of all JobInterview entities.
     */
    @Override
    public Iterable<JobInterview> allJobInterviews() {
        return findAll();
    }

}
