package core.repositories;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * Repository interface for managing JobRequirementsSpecification entities.
 *
 * @author Diogo Ribeiro
 */
public interface JobRequirementsSpecificationRepository extends DomainRepository<Integer , JobRequirementsSpecification> {

    /**
     * Retrieves all JobRequirementsSpecification entities.
     *
     * @return An Iterable containing all JobRequirementsSpecification entities.
     */
    Iterable<JobRequirementsSpecification> allJobRequirementsSpecification();

}
