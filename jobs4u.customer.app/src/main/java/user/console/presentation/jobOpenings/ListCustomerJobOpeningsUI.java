package user.console.presentation.jobOpenings;

import core.application.controllers.ListCustomerJobOpeningsController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * User Interface for listing all job openings of a specific customer.
 * This class handles the display logic for showing job openings in the console.
 *
 * @author Diogo Ribeiro
 */
public class ListCustomerJobOpeningsUI extends AbstractUI {
    private final ListCustomerJobOpeningsController theController = new ListCustomerJobOpeningsController();
    private final String email;

    public ListCustomerJobOpeningsUI(String email) {
        this.email = email;
    }

    /**
     * Displays the list of job openings for the logged in customer.
     *
     * @return true if the display was successful, false otherwise.
     */
    @Override
    protected boolean doShow() {
        List<String> jobOpenings = theController.sendCustomerJobOpenings(email);

        if (jobOpenings != null) {
            System.out.printf("%-30s%-30s%-40s%-30s\n", "Job Reference", "Active Since", "Number of Applicants", "Position");
            for (String jobOpening : jobOpenings) {
                System.out.printf("%-140s\n", jobOpening);
            }
        }

        return true;
    }

    /**
     * Provides the headline for the UI.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "List All Customer Job Openings";
    }
}