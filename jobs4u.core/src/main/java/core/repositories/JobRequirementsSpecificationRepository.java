package core.repositories;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * Repository interface for managing JobRequirementsSpecification entities.
 *
 * @author 1220812@isep.ipp.pt
 */

public interface JobRequirementsSpecificationRepository extends DomainRepository<Integer , JobRequirementsSpecification> {
    /**
     * Retrieves all JobRequirementsSpecification entities.
     *
     * @return An Iterable containing all JobRequirementsSpecification entities.
     */
    Iterable<JobRequirementsSpecification> allJobRequirementsSpecification();

}
