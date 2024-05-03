package core.repositories;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface JobRequirementsSpecificationRepository extends DomainRepository<Integer , JobRequirementsSpecification> {
    Iterable<JobRequirementsSpecification> allJobRequirementsSpecification();

    @Override
    Optional<JobRequirementsSpecification> ofIdentity(Integer id);

}
