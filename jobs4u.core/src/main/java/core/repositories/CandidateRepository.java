package core.repositories;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * Repository interface for managing {@link Candidate} entities.
 * Extends {@link DomainRepository} with {@link TelephoneNumber} as the identifier type
 * and {@link Candidate} as the entity type.
 *
 * @author Miguel Cardoso
 */
public interface CandidateRepository extends DomainRepository<TelephoneNumber, Candidate> {
    /**
     * Retrieves all candidates from the repository.
     *
     * @return an iterable containing all candidates in the repository
     */
    Iterable<Candidate> allCandidates();
}
