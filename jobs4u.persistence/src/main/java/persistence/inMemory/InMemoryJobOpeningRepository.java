package persistence.inMemory;

import core.jobOpening.domain.JobOpening;
import core.jobOpening.domain.JobReference;
import core.jobOpening.repositories.JobOpeningRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryJobOpeningRepository extends InMemoryDomainRepository<JobOpening, JobReference> implements JobOpeningRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobOpening> allJobOpenings() {
        return match(e -> true);
    }

    public Iterable<JobOpening> activeJobOpenings() {
        // Implement the method to return active job openings
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
