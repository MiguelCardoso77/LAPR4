package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.InterviewModelBuilder;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectInterviewModelUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);

    final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();

    final ListJobOpeningApplicationsController jobOpeningApplicationsController = new ListJobOpeningApplicationsController();

    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();

    final ListInterviewModelsController listInterviewModelsController = new ListInterviewModelsController();

    final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();

    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    Iterable<JobOpening> jobOpenings = new ArrayList<>();

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

        String interviewModelString = selectInterviewModel();
        List<String> data;

        try {
            data = listInterviewModelsController.importInterviewModel(Path.of(interviewModelString));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InterviewModel interviewModel = listInterviewModelsController.extractInterviewModelFromFile(data);

        if (jobInterview != null) {
            jobInterview.changeInterviewModel(interviewModel);
        }

        return false;
    }

    private void showJobOpenings() {
        jobOpenings = selectInterviewModelController.showJobOpenings();
    }

    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
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

    private String selectInterviewModel() {
        return selectInterviewModelController.listAndSelectInterviewModels();

    }

    @Override
    public String headline() {
        return "Select a Interview Model for Interviews of a Job Opening";
    }
}
