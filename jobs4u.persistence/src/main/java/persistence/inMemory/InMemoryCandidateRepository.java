package persistence.inMemory;

import core.domain.candidate.Candidate;
import core.domain.customer.TelephoneNumber;
import core.repositories.CandidateRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCandidateRepository extends InMemoryDomainRepository<Candidate, TelephoneNumber> implements CandidateRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Candidate> allCandidates() {
        return match(e -> true);
    }
}
