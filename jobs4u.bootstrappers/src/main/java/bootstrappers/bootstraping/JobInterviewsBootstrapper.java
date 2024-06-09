package bootstrappers.bootstraping;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;

public class JobInterviewsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobInterviewsBootstrapper.class);

    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final AddJobInterviewController controller = new AddJobInterviewController();
    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    private final UploadResponsesController uploadResponsesController = new UploadResponsesController();

    @Override
    public boolean execute() {
        Calendar createdOn = Calendar.getInstance();
        List<Application> applications = (List<Application>) applicationRepository.allApplications();

        registerJobInterview(createdOn, 20, 10, "Not approved", applications.get(0));
        registerJobInterview(createdOn, 20, 20, "Not approved", applications.get(1));
        registerJobInterview(createdOn, 30, 30, "Passed", applications.get(2));
        registerJobInterview(createdOn, 30, 40, "Passed", applications.get(3));
        registerJobInterview(createdOn, 40, 50, "Not approved", applications.get(4));
        registerJobInterview(createdOn, 40, 60, "Passed", applications.get(5));

        JobInterview jobInterview = listJobInterviewsApplicationController.findJobInterviewById(1);
        JobInterview jobInterview1 = listJobInterviewsApplicationController.findJobInterviewById(2);

        List<String> responses = uploadResponsesController.retrieveResponses("jobs4u.core/src/main/resources/answered/interview/interview1.txt");
        uploadResponsesController.uploadResponses(responses, jobInterview);
        uploadResponsesController.uploadResponses(responses, jobInterview1);

        return true;
    }

    private void registerJobInterview(Calendar createdOn, Integer time, Integer score, String result, Application application) {
        controller.addJobInterview(createdOn, time, score, result, application);
        LOGGER.debug("»»» {}", application);
    }
}


