package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobRequirementsService;

/**
 * Controller class for listing job requirements specifications.
 * Provides methods for retrieving, finding and list job requirements specifications.
 *
 * @author Diogo Ribeiro
 */
public class ListJobRequirementsSpecificationController {
    private final JobRequirementsService service = new JobRequirementsService();

    /**
     * Retrieves all job requirements specifications.
     *
     * @return An Iterable containing all job requirements specifications.
     */
    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return service.allJobRequirementsSpecification();
    }

    /**
     * Finds a job requirements specification by its ID.
     *
     * @param id The ID of the job requirements specification to find.
     * @return The found JobRequirementsSpecification object, or null if not found.
     */
    public JobRequirementsSpecification findJobRequirementSpecification(Integer id) {
        return service.findJobRequirementsSpecification(id);
    }
}