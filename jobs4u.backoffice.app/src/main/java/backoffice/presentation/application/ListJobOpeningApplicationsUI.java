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


public class ListJobOpeningApplicationsUI extends AbstractListUI<JobOpening> {
    private final ListJobOpeningApplicationsController theController = new ListJobOpeningApplicationsController();

    private final ListJobOpeningController theController1 = new ListJobOpeningController();


    private static final Logger LOGGER = LoggerFactory.getLogger(ListJobOpeningApplicationsUI.class);

    @Override
    public String headline() {
        return "List All Applications of a Job Opening";
    }

    @Override
    protected String emptyMessage() {
        return "No applications found for this job opening.";
    }

    protected Iterable<Application> elementsApp(JobReference jobReference) {
        return theController.allApplicationsOfJobOpening(jobReference);
    }

    @Override
    protected Iterable<JobOpening> elements() {
        return theController1.allJobOpening();
    }

    @Override
    protected Visitor<JobOpening> elementPrinter() {
        return null;
    }

    @Override
    protected String elementName() {
        return "Application";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-30s%-30s%-30s", "ID", "Submission Date", "Rank");
    }

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

        final Iterable<Application> iterable1 = elementsApp(jobOpeningApplication.jobReference());
        if(!iterable1.iterator().hasNext()){
            System.out.println("There is no applications for this job opening ");
        }else {


            for (Application application : iterable1) {
                System.out.printf("%-30s%-30s%-30s%-30s%-30s", application.idApplication(), application.rank(), application.submissionDate(), application.status(), application.applicationDataFile());
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", application.applicationDataFile(), application.filesAttachedContent(), application.emailFilesAttached(), application.emailContentFile(), application.jobReference());
            }
        }

        return true;
    }
}
