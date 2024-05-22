package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobOpeningService;
import eapli.framework.application.UseCaseController;

/**
 * Controller class for managing job requirements specifications.
 * Provides methods for retrieving, finding, importing, and extracting job requirements specifications from files.
 * Acts as an intermediary between the presentation layer and the service layer.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class UpdateJobOpeningRequirementsController {
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    /**
     * Updates a job opening with the given job requirements specification.
     *
     * @param jobReference The reference of the job opening to be updated.
     * @param jobRequirementsSpecification The job requirements specification to be associated with the job opening.
     * @return The updated JobOpening object.
     */
    public JobOpening updateJobOpening(JobReference jobReference, JobRequirementsSpecification jobRequirementsSpecification){
        return jobOpeningService.updateJobRequirements(jobReference, jobRequirementsSpecification);
    }
}
