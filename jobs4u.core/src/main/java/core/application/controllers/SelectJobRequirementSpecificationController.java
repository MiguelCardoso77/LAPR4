package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;

import java.util.List;

/**
 * Controller class for selecting one job requirements specifications.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class SelectJobRequirementSpecificationController {
    final ListJobRequirementsSpecificationController listJobRequirementsSpecificationController = new ListJobRequirementsSpecificationController();

    /**
     * Lists all job requirements specifications and allows the user to select one.
     *
     * @return The selected JobRequirementsSpecification object, or null if no selection was made.
     */
    public JobRequirementsSpecification selectJobRequirementSpecification() {
        final List<JobRequirementsSpecification> requirements = (List<JobRequirementsSpecification>) listJobRequirementsSpecificationController.allJobRequirementsSpecification();
        return selectorPart(requirements);
    }

    /**
     * Handles the selection of a job requirements specification from a provided list.
     *
     * @param list The list of job requirements specifications to select from.
     * @return The selected JobRequirementsSpecification object, or null if no selection was made.
     */
    public JobRequirementsSpecification selectorPart(List<JobRequirementsSpecification> list) {
        JobRequirementsSpecification requirement = null;

        final int option = Console.readInteger("\nEnter the number of the job requirements that you want to select: ");
        if (option == 0) {
            System.out.println("No job requirement selected");
        } else {
            try {
                requirement = listJobRequirementsSpecificationController.findJobRequirementSpecification(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                System.out.println("There was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return requirement;
    }
}