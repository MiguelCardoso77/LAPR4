package core.repositories;

import core.domain.candidate.Candidate;
import core.domain.customer.TelephoneNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface CandidateRepository extends DomainRepository<TelephoneNumber, Candidate> {
    Iterable<Candidate> allCandidates();
}
