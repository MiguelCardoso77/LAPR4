package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;

import java.util.List;


/**
 * The SelectJobOpeningController class provides methods to select a job opening
 * from a list of job openings. It interacts with the ListJobOpeningController
 * to fetch job openings and allows the user to choose one based on its index.
 *
 * @author Diana Neves
 */
public class SelectJobOpeningController {
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();

    /**
     * Displays a list of job openings and allows the user to select one.
     *
     * @return The selected JobOpening object.
     */
    public JobOpening selectJobOpening() {
        final List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningController.showJobOpenings();
        return selectorPart(jobOpenings);
    }

    /**
     * Displays a list of job openings for analysis and allows the user to select one.
     *
     * @return The selected JobOpening object.
     */
    public JobOpening selectJobOpeningAnalysis() {
        final List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningController.showJobOpeningsAnalysis();
        return selectorPart(jobOpenings);
    }

    /**
     * Allows the user to select a job opening from the provided list.
     *
     * @param list A list of JobOpening objects to choose from.
     * @return The selected JobOpening object, or null if no job opening was selected.
     */
    public JobOpening selectorPart(List<JobOpening> list) {
        JobOpening jobOpening = null;

        final int option = Console.readInteger("\nEnter the number of the job opening: ");
        if (option == 0) {
            System.out.println("No job opening selected");
        } else {
            try {
                jobOpening = jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                System.out.println("There was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }
}
