package core.services;

import core.domain.candidate.Candidate;
import core.domain.candidate.CandidateBuilder;
import core.domain.candidate.Curriculum;
import core.domain.candidate.TelephoneNumber;
import core.persistence.PersistenceContext;
import core.repositories.CandidateRepository;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing candidates.
 *
 * @author Miguel Cardoso
 */
@Service
public class CandidateService {
    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

    /**
     * Registers a new candidate with the provided information.
     *
     * @param systemUser      the system user associated with the candidate
     * @param telephoneNumber the telephone number of the candidate
     * @param curriculum      the curriculum of the candidate
     * @return the registered candidate
     */
    @Transactional
    public Candidate registerCandidate(final SystemUser systemUser, final String telephoneNumber, final String curriculum) {
        TelephoneNumber telephoneNumberObj = new TelephoneNumber(telephoneNumber);
        Curriculum curriculumObj = new Curriculum(curriculum);

        Candidate candidate = createCandidate(systemUser, telephoneNumberObj, curriculumObj);

        return candidateRepository.save(candidate);
    }

    /**
     * Creates a new candidate with the provided information.
     *
     * @param systemUser      the system user associated with the candidate
     * @param telephoneNumber the telephone number of the candidate
     * @param curriculum      the curriculum of the candidate
     * @return the created candidate
     */
    @Transactional
    public Candidate createCandidate(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        candidateBuilder.withAll(systemUser, telephoneNumber, curriculum);
        Candidate newCandidate = candidateBuilder.build();

        return this.candidateRepository.save(newCandidate);
    }

    /**
     * Finds a candidate by telephone number.
     *
     * @param telephoneNumber the telephone number of the candidate to find
     * @return the candidate if found, otherwise null
     */
    public Candidate findCandidate(TelephoneNumber telephoneNumber) {
        Iterable<Candidate> candidates = candidateRepository.allCandidates();

        for (Candidate candidate : candidates) {
            if (candidate.identity().equals(telephoneNumber)) {
                return candidate;
            }
        }

        return null;
    }

    /**
     * Retrieves all candidates.
     *
     * @return an iterable of all candidates
     */
    public Iterable<Candidate> allCandidates() {
        return candidateRepository.allCandidates();
    }

    /**
     * Retrieve the Candidate associated with the telephone number passed by parameter
     *
     * @param telephoneNumber candidate´s telephone number
     * @return candidate
     */
    public Candidate findCandidateByTelephoneNumber(TelephoneNumber telephoneNumber) {
        Optional<Candidate> candidateOptional = candidateRepository.findByTelephoneNumber(telephoneNumber);
        return candidateOptional.orElse(null);
    }

    /**
     * Finds candidate by email.
     * @param email email of the candidate
     *
     * @return candidate if found, otherwise null
     */
    public Candidate findCandidateByEmail(String email) {
        EmailAddress emailAddress = EmailAddress.valueOf(email);

        Iterable<Candidate> allCandidates = allCandidates();
        if (allCandidates.iterator().hasNext()) {
            for (Candidate candidate : allCandidates) {
                if (candidate.user().email().equals(emailAddress)) {
                    return candidate;
                }
            }
        }

        return null;
    }
}
