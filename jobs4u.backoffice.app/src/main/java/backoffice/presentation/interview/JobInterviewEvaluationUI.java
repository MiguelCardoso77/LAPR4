package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JobInterviewEvaluationUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");
        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        System.out.println("\nRegistered applications: ");



        return false;
    }

    private void showJobOpenings(){
        final Iterable<JobOpening> iterable = jobOpeningController.allJobOpening();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job openings");
        }else{
            int cont = 1;
            System.out.println("List of registered Job Openings");
            for (JobOpening jobOpening : iterable) {
                // TO DO: Mostrar apenas as que estao na fase analysis
                    System.out.printf("%-6s%-30s%n", cont, jobOpening.identity());
                    cont++;

            }
        }
    }

    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningController.allJobOpening()) {
            list.add(jobOpening);
        }
        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
            System.exit(0);
        } else {
            try {
                jobOpening = this.jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }

    @Override
    public String headline() {
        return "Job interview answers evaluation process";
    }
}
