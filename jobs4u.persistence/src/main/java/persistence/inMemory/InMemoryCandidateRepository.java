package persistence.inMemory;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.repositories.CandidateRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryCandidateRepository extends InMemoryDomainRepository<Candidate, TelephoneNumber> implements CandidateRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Candidate> allCandidates() {
        return match(e -> true);
    }

    @Override
    public Optional<Candidate> findByTelephoneNumber(TelephoneNumber telephoneNumber) {
        return Optional.empty();
    }

    /**
     * Retrieves the Candidate associated to the telephone number passed by parameter
     *
     * @param telephoneNumber candidate´s telephone number
     * @return candidate
     */
    @Override
    public Optional<Candidate> findByTelephoneNumber(TelephoneNumber telephoneNumber) {
        return Optional.empty();
    }
}
