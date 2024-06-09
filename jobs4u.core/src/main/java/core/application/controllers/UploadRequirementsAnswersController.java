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

/**
 * Controller class responsible for handling the uploading of requirement answers for a candidate's application.
 * This controller coordinates the interaction between the UI and the domain layer to manage the uploading of requirement answers.
 *
 * @author Diana Neves
 */
public class UploadRequirementsAnswersController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final ApplicationService applicationService = new ApplicationService();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final GenerateRequirementsSpecificationController generateRequirementsSpecificationController = new GenerateRequirementsSpecificationController();
    private final CandidateRequirementsService candidateRequirementsService = new CandidateRequirementsService();

    /**
     * Uploads the requirement answers provided by the candidate for a specific application.
     *
     * @param requirements The list of requirement answers provided by the candidate
     * @param application  The application for which the requirement answers are uploaded
     */
    public void uploadRequirements(List<String> requirements, Application application) {
        CandidateRequirements candidateRequirements = candidateRequirementsService.registerCandidateRequirements(requirements);

        applicationService.uploadCandidateRequirements(application, candidateRequirements);
        applicationRepository.save(application);
    }

    /**
     * Retrieves the requirement answers from a file located at the specified path.
     *
     * @param path The path to the file containing the requirement answers
     * @return The list of requirement answers retrieved from the file
     */
    public List<String> retrieveResponseRequirements(String path) {
        RequirementsPlugin plugin = new RequirementsPlugin();
        return plugin.retrieveAnswersRequirements(path);
    }

    /**
     * Finds all job openings that have job requirements specified.
     *
     * @return The list of job openings with job requirements
     */
    public List<JobOpening> findAllJobOpeningsWithJobRequirements() {
        return generateRequirementsSpecificationController.findAllJobOpeningAssigned();
    }

    /**
     * Shows the applications associated with a specific job opening.
     *
     * @param jobReference The reference of the job opening
     * @return The iterable collection of applications associated with the job opening
     */
    public Iterable<Application> showApplicationsOfJobOpening(JobReference jobReference) {
        return listJobOpeningApplicationsController.showApplicationsOfJobOpening(jobReference);
    }

    /**
     * Selects an application from the list of applications associated with job openings.
     *
     * @return The selected application
     */
    public Application selectApplication() {
        return listJobOpeningApplicationsController.selectApplication();
    }

    /**
     * Selects a job opening from the list of job openings with requirements.
     *
     * @param requirements The list of job openings with requirements
     * @return The selected job opening
     */
    public JobOpening selectJobOpening(List<JobOpening> requirements) {
        return selectJobOpeningController.selectorPart(requirements);
    }
}
