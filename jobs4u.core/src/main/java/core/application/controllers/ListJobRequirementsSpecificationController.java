package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobRequirementsService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller class for managing job requirements specifications.
 * Provides methods for retrieving, finding, importing, and extracting job requirements specifications from files.
 * Acts as an intermediary between the presentation layer and the service layer.
 *
 * @author 1220812@isep.ipp.pt
 */
public class ListJobRequirementsSpecificationController {

    private final JobRequirementsService service = new JobRequirementsService();

    /**
     * Retrieves all job requirements specifications.
     *
     * @return An Iterable containing all job requirements specifications.
     */

    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification(){
        return service.allJobRequirementsSpecification();
    }

    /**
     * Finds a job requirements specification by its ID.
     *
     * @param id The ID of the job requirements specification to find.
     * @return The found JobRequirementsSpecification object, or null if not found.
     */

    public JobRequirementsSpecification findJobRequirementSpecification(Integer id){
        return service.findJobRequirementsSpecification(id);
    }

    public JobRequirementsSpecification registerJobRequirementsSpecification(String jobRequirementsFilePath){
        return service.registerJobRequirement(jobRequirementsFilePath);
    }
}