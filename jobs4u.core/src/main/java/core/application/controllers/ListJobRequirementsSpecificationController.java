package core.application.controllers;

import core.domain.jobOpening.JobReference;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobOpeningService;
import core.services.JobRequirementsService;
import eapli.framework.io.util.Console;

import java.io.*;
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

    private final JobOpeningService jobOpeningService = new JobOpeningService();

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

    public JobRequirementsSpecification listAndSelectJobRequirementsSpecification(){
        List<JobRequirementsSpecification> jobRequirementsSpecifications = (List<JobRequirementsSpecification>) service.allJobRequirementsSpecification();
        for (JobRequirementsSpecification requirement :jobRequirementsSpecifications) {
            System.out.println(requirement);
        }
        int option;
        do{
            option = Console.readInteger("Enter the number of the file you want to select (0 to cancel): ");
            if (option < 0 || option > jobRequirementsSpecifications.size()) {
                System.out.println("Invalid option. Please select a number between 1 and " + jobRequirementsSpecifications.size() + ".");
            }
        }while (option < 0 || option > jobRequirementsSpecifications.size());
        return (option == 0) ? null : jobRequirementsSpecifications.get(option - 1);
    }

    public void updateJobOpening(JobReference jobReference, JobRequirementsSpecification jobRequirementsSpecification){
        jobOpeningService.updateJobRequirements(jobReference, jobRequirementsSpecification);
    }

}