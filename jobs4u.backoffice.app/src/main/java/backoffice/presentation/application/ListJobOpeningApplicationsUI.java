package backoffice.presentation.application;

import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * User interface for listing all applications of a job opening.
 *
 * @author Tomás Gonçalves
 */

public class ListJobOpeningApplicationsUI extends AbstractListUI<JobOpening> {
    private final ListJobOpeningApplicationsController theController = new ListJobOpeningApplicationsController();

    private final ListJobOpeningController theController1 = new ListJobOpeningController();


    private static final Logger LOGGER = LoggerFactory.getLogger(ListJobOpeningApplicationsUI.class);

    /**
     * Generates the headline for this UI.
     *
     * @return The headline string.
 * */
    @Override
    public String headline() {
        return "List All Applications of a Job Opening";
    }
    /**
     * Provides the message to display when no applications are found.
     *
     * @return The message indicating no applications found.
 */
    @Override
    protected String emptyMessage() {
        return "No applications found for this job opening.";
    }


    /**
     * Retrieves all applications for a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications for the specified job opening.
     */
    protected Iterable<Application> elementsApp(JobReference jobReference) {
        return theController.allApplicationsOfJobOpening(jobReference);
    }



    /**
     * Retrieves all job openings.
     *
     * @return Iterable of all job openings.
     */
    @Override
    protected Iterable<JobOpening> elements() {
        return theController1.allJobOpening();
    }

    @Override
    protected Visitor<JobOpening> elementPrinter() {
        return null;
    }


    /**
     * Specifies the name of the elements being listed.
     *
     * @return The name of the elements being listed.
     */
    @Override
    protected String elementName() {
        return "Application";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-30s%-30s%-30s", "ID", "Submission Date", "Rank");
    }


    /**
     * Displays all applications of a selected job opening.
     *
     * @return true if the operation is successful, otherwise false.
     */
    @Override
    public boolean doShow() {
        final List<JobOpening> list = new ArrayList<>();
        final Iterable<JobOpening> iterable = elements();
        JobOpening jobOpeningApplication = null;
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no job openings ");
        } else {
            int cont = 1;
            System.out.println("Select a Job Opening: \n");
            System.out.printf("%-30s%-30s%n", "Title or Function:", "Job Reference:");
            for (JobOpening jobOpening : iterable) {
                list.add(jobOpening);
                System.out.printf("%-6s%-30s%-30s%n",cont, jobOpening.titleOrFunction(), jobOpening.jobReference());
                cont++;
            }
            final int option = Console.readInteger("Enter the number of job opening");
            if (option == 0) {
                System.out.println("No job opening selected");
            } else {
                try {
                    jobOpeningApplication = this.theController.findJobOpening(list.get(option - 1).jobReference());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                }
            }
        }
        if (jobOpeningApplication != null) {

            final Iterable<Application> iterable1 = theController.allApplicationsOfJobOpening(jobOpeningApplication.jobReference());
            if (!iterable1.iterator().hasNext()) {
                System.out.println("There are no applications for this job opening ");
            } else {
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Submission Date", "Status", "Data File", "Job Reference");
                for (Application application : iterable1) {
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%n", application.identity(), application.rank(), application.submissionDate(), application.status(), application.dataFile(), application.jobReference());
                }
            }

        }
        return true;
    }
}

