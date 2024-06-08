package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.services.ApplicationService;
import core.services.CandidateRequirementsService;
import plugin.requirements.RequirementsPlugin;
import java.util.List;

public class UploadRequirementsAnswersController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final ApplicationService applicationService = new ApplicationService();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final GenerateRequirementsSpecificationController generateRequirementsSpecificationController = new GenerateRequirementsSpecificationController();
    private final CandidateRequirementsService candidateRequirementsService = new CandidateRequirementsService();

    public void uploadRequirements(List<String> requirements, Application application) {
        CandidateRequirements candidateRequirements = candidateRequirementsService.registerCandidateRequirements(requirements);

        applicationService.uploadCandidateRequirements(application, candidateRequirements);
        applicationRepository.save(application);
    }

    public List<String> retrieveResponseRequirements(String path) {
        RequirementsPlugin plugin = new RequirementsPlugin();
        return plugin.retrieveAnswersRequirements(path);
    }

    public List<JobOpening> findAllJobOpeningsWithJobRequirements() {
        return generateRequirementsSpecificationController.findAllJobOpeningAssigned();
    }

    public Iterable<Application> showApplicationsOfJobOpening(JobReference jobReference) {
        return listJobOpeningApplicationsController.showApplicationsOfJobOpening(jobReference);
    }

    public Application selectApplication() {
        return listJobOpeningApplicationsController.selectApplication();
    }

    public JobOpening selectJobOpening(List<JobOpening> requirements) {
        return selectJobOpeningController.selectorPart(requirements);
    }
}
