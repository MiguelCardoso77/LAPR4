package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.ListJobInterviewsApplicationController;
import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.application.controllers.SelectInterviewModelController;
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

    Iterable<JobOpening> jobOpenings = new ArrayList<>();

    final ListJobOpeningApplicationsController jobOpeningApplicationsController = new ListJobOpeningApplicationsController();

    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();

    Iterable<Application> applicationList = new ArrayList<>();

    Iterable<JobInterview> jobInterviews = new ArrayList<>();

    final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();

    List<String> interviewModels = new ArrayList<>();


    @Override
    protected boolean doShow() {

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        showApplicationsOfJobOpening(jobOpening);
        Application application = selectApplication();

        showInterviewsOfApplication(application);
        JobInterview jobInterview = selectJobInterview();

        listInterviewModels();

        InterviewModel interviewModel = selectInterviewModel(interviewModels);
        jobInterview.changeInterviewModel(interviewModel);

        return false;
    }

    private void showJobOpenings() {
        jobOpenings = selectInterviewModelController.showJobOpenings();
    }

    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        if (jobOpenings.iterator().hasNext()) {
            for (JobOpening jobOpening : jobOpenings) {
                list.add(jobOpening);
            }

            JobOpening jobOpening = null;
            final int option = Console.readInteger("Enter the number of the job opening");
            if (option == 0) {
                System.out.println("No job opening selected");
            } else {
                try {
                    jobOpening = this.jobOpeningController.findJobOpeningByJobReference(list.get(option - 1 ).identity());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
                }
            }

            return jobOpening;
        }
        return null;
    }

    private void showApplicationsOfJobOpening(JobOpening jobOpening) {
        applicationList = selectInterviewModelController.showApplicationsOfJobOpening(jobOpening);
    }

    private Application selectApplication() {
        final List<Application> list = new ArrayList<>();
        if (applicationList.iterator().hasNext()) {
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

        return null;
    }

    private void showInterviewsOfApplication(Application application) {
        jobInterviews = selectInterviewModelController.showInterviewsOfApplication(application);
    }

    private JobInterview selectJobInterview() {
        final List<JobInterview> list = new ArrayList<>();
        if (jobInterviews.iterator().hasNext()) {
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
        return null;
    }

    private void listInterviewModels() {
        interviewModels = selectInterviewModelController.listInterviewModels();
    }

    private InterviewModel selectInterviewModel(List<String> listInterviewModels) {
        InterviewModel interviewModel = null;

        final int option = Console.readInteger("Enter the number of the Interview Model");
        if (option == 0) {
            System.out.println("No interview model selected");
        } else {
            try {
                interviewModel = new InterviewModel(listInterviewModels.get(option));
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return interviewModel;
    }

    @Override
    public String headline() {
        return "Select a Interview Model for Interviews of a Job Opening";
    }
}
