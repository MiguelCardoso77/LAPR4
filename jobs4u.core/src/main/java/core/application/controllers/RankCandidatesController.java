package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;
import eapli.framework.application.UseCaseController;

import java.util.List;

@UseCaseController
public class RankCandidatesController {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    public List<JobOpening> getAllJobOpenings() {
        return (List<JobOpening>) jobOpeningRepository.findAll();
    }
}
