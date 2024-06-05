package user.console.presentation.jobOpenings;

import core.application.controllers.ListCustomerJobOpeningsController;
import core.domain.jobOpening.JobOpeningDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;


/**
 * User Interface for listing all job openings of a specific customer.
 * This class handles the display logic for showing job openings in the console.
 *
 * @author 1220812@isep.ipp.pt
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
        List<JobOpeningDTO> jobOpenings = theController.sendCustomerJobOpenings(email);

        if(jobOpenings != null){
            System.out.printf("%-30s%-30s%-60s%-20s\n", "Job Reference", "Position", "Active Since", "Number of Applicants");
            for (JobOpeningDTO jobOpening : jobOpenings) {
                System.out.printf("%-30s%-30s%-60s%-20s\n", jobOpening.myJobReference(), jobOpening.myPosition(), jobOpening.myActiveSince(), jobOpening.myNumberOfApplicants());
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
        return "List all customer Job Openings";
    }
}