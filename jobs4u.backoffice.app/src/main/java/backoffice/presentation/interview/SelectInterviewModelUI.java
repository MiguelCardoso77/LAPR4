package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.interviewModel.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;

public class SelectInterviewModelUI extends AbstractUI {

    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    Iterable<Application> applicationList = new ArrayList<>();

    @Override
    protected boolean doShow() {

        JobOpening jobOpening = selectJobOpening();

        InterviewModel interviewModel = selectInterviewModel();

        applicationsOfJobOpening(jobOpening);

        updateInterviewModel(jobOpening, interviewModel);

        return false;
    }

    private void updateInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
        if (!applicationList.iterator().hasNext()) {
            System.out.println("It's not possible to perform this operation. ");
            doShow();
        } else {
            for (Application application : applicationList) {
                Iterable<JobInterview> jobInterviews = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
                for (JobInterview jobInterview : jobInterviews) {
                    if (jobInterview != null) {
                        selectInterviewModelController.updateInterviewModel(jobOpening.jobReference(), interviewModel);
                    }
                }
            }
        }
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
