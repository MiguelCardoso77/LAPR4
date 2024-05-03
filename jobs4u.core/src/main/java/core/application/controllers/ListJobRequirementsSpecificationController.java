package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobRequirementsService;

public class ListJobRequirementsSpecificationController {

    private final JobRequirementsService service = new JobRequirementsService();

    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification(){
        return service.allJobRequirementsSpecification();
    }

    public JobRequirementsSpecification findJobRequirementSpecification(Integer id){
        return service.findJobRequirementsSpecification(id);
    }

}
