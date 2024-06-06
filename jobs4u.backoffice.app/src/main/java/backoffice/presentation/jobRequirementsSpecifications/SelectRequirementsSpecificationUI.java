package backoffice.presentation.jobRequirementsSpecifications;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.ListJobRequirementsSpecificationController;
import core.application.controllers.SelectJobOpeningController;
import core.application.controllers.SelectJobRequirementSpecificationController;
import core.application.controllers.UpdateJobOpeningRequirementsController;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.presentation.console.AbstractUI;

/**
 * This class represents the user interface for selecting job requirements specifications and associating them with a job opening.
 *
 * @author 1220812@isep.ipp.pt
 */
public class SelectRequirementsSpecificationUI extends AbstractUI {
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final SelectJobRequirementSpecificationController selectJobRequirementSpecificationController = new SelectJobRequirementSpecificationController();
    private final ListJobRequirementsSpecificationController listJobRequirementsSpecification = new ListJobRequirementsSpecificationController();
    private final UpdateJobOpeningRequirementsController updateJobOpening = new UpdateJobOpeningRequirementsController();

    /**
     * Displays the user interface for selecting job requirements specifications and associating them with a job opening.
     *
     * @return false always, as this UI does not control the application flow.
     */
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");

        JobOpening jobOpening = selectJobOpening();

        System.out.println("\nAvailable Requirements Specifications: ");
        showJobRequirements();

        JobRequirementsSpecification jobRequirementsSpecification = selectRequirement();

        JobOpening updatedJobOpening = updateJobOpening.updateJobOpening(jobOpening.jobReference(), jobRequirementsSpecification);

        if (updatedJobOpening.jobRequirementsSpecification().identity() != null) {
            System.out.println(ConsoleColors.GREEN + "\nRequirements specifications selected!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "\nFailed to select the requirements specifications." + ConsoleColors.RESET);
        }

        return false;
    }

    /**
     * Displays the list of available job requirements specifications.
     */
    private void showJobRequirements() {
        final Iterable<JobRequirementsSpecification> iterable = listJobRequirementsSpecification.allJobRequirementsSpecification();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no job requirements registered");
        } else {
            System.out.println("List of registered job requirements");
            for (JobRequirementsSpecification jobRequirementsSpecification : iterable) {
                System.out.printf("%-6s%-30s%n", jobRequirementsSpecification.identity(), jobRequirementsSpecification.jobRequirementsPath());
            }
        }
    }

    /**
     * Prompts the user to select a job requirements specification from the list of available specifications.
     *
     * @return The selected JobRequirementsSpecification object, or null if no selection was made.
     */
    private JobRequirementsSpecification selectRequirement() {
        return selectJobRequirementSpecificationController.selectJobRequirementSpecification();
    }

    /**
     * Prompts the user to select a job opening from the list of available job openings.
     *
     * @return The selected JobOpening object.
     */
    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Provides the headline for this UI.
     *
     * @return A string containing the headline for this UI.
     */
    @Override
    public String headline() {
        return "Select Requirements Specifications";
    }
}