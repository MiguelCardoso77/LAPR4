package core.repositories;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.DomainRepository;

public interface JobRequirementsSpecificationRepository extends DomainRepository<Integer , JobRequirementsSpecification> {
    Iterable<JobRequirementsSpecification> allJobRequirementsSpecification();
}
