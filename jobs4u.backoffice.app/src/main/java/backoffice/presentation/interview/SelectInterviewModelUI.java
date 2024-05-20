package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.*;
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

    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    Iterable<JobOpening> jobOpenings = new ArrayList<>();
    Iterable<Application> applicationList = new ArrayList<>();

    @Override
    protected boolean doShow() {

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        InterviewModel interviewModel = selectInterviewModel();

        applicationsOfJobOpening(jobOpening);

        updateInterviewModel(interviewModel);

        return false;
    }

    private void updateInterviewModel(InterviewModel interviewModel) {
        if (!applicationList.iterator().hasNext()) {
            System.out.println("It's not possible to perform this operation. ");
            doShow();
        } else {
            for (Application application : applicationList) {
                Iterable<JobInterview> jobInterviews = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
                for (JobInterview jobInterview : jobInterviews) {
                    if (jobInterview != null) {
                        selectInterviewModelController.updateInterviewModel(interviewModel, jobInterview.identity());
                    }
                }
            }
        }
    }

    private void showJobOpenings() {
        jobOpenings = listJobOpeningController.showJobOpenings();
    }

    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    private void applicationsOfJobOpening(JobOpening jobOpening) {
        applicationList = selectInterviewModelController.applicationsOfJobOpening(jobOpening);
    }

    private InterviewModel selectInterviewModel() {
        return selectInterviewModelController.listAndSelectInterviewModels();

    }

    @Override
    public String headline() {
        return "Select a Interview Model for Interviews of a Job Opening";
    }
}
