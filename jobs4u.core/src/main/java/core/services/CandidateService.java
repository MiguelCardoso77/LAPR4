package core.services;

import core.domain.candidate.Candidate;
import core.domain.candidate.CandidateBuilder;
import core.domain.candidate.Curriculum;
import core.domain.customer.TelephoneNumber;
import core.persistence.PersistenceContext;
import core.repositories.CandidateRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;

public class CandidateService {
    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

    @Transactional
    public Candidate registerCandidate(final SystemUser systemUser, final String telephoneNumber, final String curriculum) {
        TelephoneNumber telephoneNumberObj = new TelephoneNumber(telephoneNumber);
        Curriculum curriculumObj = new Curriculum(curriculum);

        Candidate candidate = createCandidate(systemUser, telephoneNumberObj, curriculumObj);

        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate createCandidate(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        CandidateBuilder candidateBuilder = new CandidateBuilder();
        candidateBuilder.withAll(systemUser, telephoneNumber, curriculum);
        Candidate newCandidate = candidateBuilder.build();

        return this.candidateRepository.save(newCandidate);
    }
}
