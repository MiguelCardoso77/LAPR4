package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobRequirementsService;
import eapli.framework.application.UseCaseController;

/**
 * Controller class for adding job requirements specifications.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class AddJobRequirementSpecificationController {
    private final JobRequirementsService service = new JobRequirementsService();

    /**
     * Registers a new job requirements specification from the given file path.
     *
     * @param jobRequirementsFilePath The file path of the job requirements specification to be registered.
     * @return The registered JobRequirementsSpecification object.
     */
    public JobRequirementsSpecification registerJobRequirementsSpecification(String jobRequirementsFilePath){
        return service.registerJobRequirement(jobRequirementsFilePath);
    }
}