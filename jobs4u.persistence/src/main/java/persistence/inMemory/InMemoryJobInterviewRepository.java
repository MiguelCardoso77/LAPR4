package persistence.inMemory;

import core.domain.interview.JobInterview;
import core.repositories.JobInterviewRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryJobInterviewRepository extends InMemoryDomainRepository<JobInterview, Integer> implements JobInterviewRepository{
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<JobInterview> allJobInterviews() {
        return match(e -> true);
    }
}
