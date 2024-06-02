package persistence.inMemory;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryJobRequirementsSpecificationRepository extends InMemoryDomainRepository<JobRequirementsSpecification, Integer> implements JobRequirementsSpecificationRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return match(e -> true);
    }
}
