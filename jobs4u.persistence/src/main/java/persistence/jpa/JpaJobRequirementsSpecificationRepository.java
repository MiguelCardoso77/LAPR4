package persistence.jpa;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the JobRequirementsSpecificationRepository interface.
 * This repository provides access to JobRequirementsSpecification entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaJobRequirementsSpecificationRepository extends JpaAutoTxRepository<JobRequirementsSpecification, Integer, Integer> implements JobRequirementsSpecificationRepository {

    /**
     * Constructs a new JpaJobRequirementsSpecificationRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     */
    public JpaJobRequirementsSpecificationRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Constructs a new JpaJobRequirementsSpecificationRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaJobRequirementsSpecificationRepository(final String puname) {
        super(puname, "idJobRequirementsSpecification");
    }

    /**
     * Retrieves all JobRequirementsSpecification entities.
     *
     * @return an iterable collection of all JobRequirementsSpecification entities.
     */
    @Override
    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return findAll();
    }
}
