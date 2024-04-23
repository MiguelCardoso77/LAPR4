package persistence.inMemory;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.repositories.JobOpeningRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryJobOpeningRepository extends InMemoryDomainRepository<JobOpening, JobReference> implements JobOpeningRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobOpening> allJobOpenings() {
        return match(e -> true);
    }

    @Override
    public JobOpening findJobOpening() {
        return null;
    }

    public Iterable<JobOpening> activeJobOpenings() {
        // Implement the method to return active job openings
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
