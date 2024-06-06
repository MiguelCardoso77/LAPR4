package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import plugin.requirements.RequirementsPlugin;
import java.util.List;

public class UploadRequirementsAnswersController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final GenerateRequirementsSpecificationController generateRequirementsSpecificationController = new GenerateRequirementsSpecificationController();

    public void uploadRequirements(List<String> requirements, Application application) {
        CandidateRequirements candidateRequirements = new CandidateRequirements(requirements);

        application.uploadCandidateRequirements(candidateRequirements);
        applicationRepository.save(application);
    }

    public List<String> retrieveResponseRequirements(String path) {
        RequirementsPlugin plugin = new RequirementsPlugin();
        return plugin.retrieveAnswersRequirements(path);
    }

    public List<JobOpening> findAllJobOpeningsWithJobRequirements() {
        return generateRequirementsSpecificationController.findAllJobOpeningAssigned();
    }
}
