package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.ListJobInterviewsApplicationController;
import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SelectInterviewModelUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);

    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();

    final Iterable<JobOpening> iterable = jobOpeningController.allJobOpening();

    final ListJobOpeningApplicationsController jobOpeningApplicationsController = new ListJobOpeningApplicationsController();

    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();

    Iterable<Application> applicationList = new ArrayList<>();

    Iterable<JobInterview> jobInterviews = new ArrayList<>();


    @Override
    protected boolean doShow() {

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        showApplicationsOfJobOpening(jobOpening);
        Application application = selectApplication();

        showInterviewsOfApplication(application);
        JobInterview jobInterview = selectJobInterview();

        //falta dar list dos interviews model e pedir para escolher
        jobInterview.changeInterviewModel(new InterviewModel("Colocar caminho da escolha de cima aqui :)"));


        return false;
    }


    private void showJobOpenings() {
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings: \n");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
        }
    }

    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : iterable) {
            list.add(jobOpening);
        }

        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
        } else {
            try {
                jobOpening = this.jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return jobOpening;
    }

    private void showApplicationsOfJobOpening(JobOpening jobOpening) {
        applicationList = jobOpeningApplicationsController.allApplicationsOfJobOpening(jobOpening.identity());

        if (!applicationList.iterator().hasNext()) {
            System.out.println("There are no Applications for this jobOpening");
        } else {
            int cont = 1;
            System.out.println("List of registered Applications: \n");
            for (Application application : applicationList) {
                System.out.printf("%-6s%-30s%n", cont, application.candidate());
                cont++;
            }
        }
    }

    private Application selectApplication() {
        final List<Application> list = new ArrayList<>();
        for (Application application : applicationList) {
            list.add(application);
        }

        Application application = null;
        final int option = Console.readInteger("Enter the number of the application");
        if (option == 0) {
            System.out.println("No applications selected");
        } else {
            try {
                application = this.jobOpeningApplicationsController.findApplicationByID(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return application;
    }

    private void showInterviewsOfApplication(Application application) {
        jobInterviews = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
    }

    private JobInterview selectJobInterview() {
        final List<JobInterview> list = new ArrayList<>();
        for (JobInterview jobInterview : jobInterviews) {
            list.add(jobInterview);
        }

        JobInterview jobInterview = null;
        final int option = Console.readInteger("Enter the number of the jobInterview");
        if (option == 0) {
            System.out.println("No job interviews selected");
        } else {
            try {
                jobInterview = listJobInterviewsApplicationController.findJobInterviewById(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return jobInterview;
    }

    @Override
    public String headline() {
        return "Select a Interview Model for Interviews of a Job Opening";
    }
}
