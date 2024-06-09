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

/**
 * Bootstraps job interview data.
 * This bootstrapper registers sample job interviews using the AddJobInterviewController.
 * Requires the ApplicationRepository, AddJobInterviewController, ListJobInterviewsApplicationController, and UploadResponsesController to execute.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Diana Neves
 */
public class JobInterviewsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobInterviewsBootstrapper.class);

    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final AddJobInterviewController controller = new AddJobInterviewController();
    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    private final UploadResponsesController uploadResponsesController = new UploadResponsesController();

    /**
     * Executes the bootstrapping process for job interview data.
     * Registers sample job interviews using the AddJobInterviewController.
     * @return true if bootstrapping is successful, false otherwise
     */
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

    /**
     * Registers a job interview using the AddJobInterviewController.
     * @param createdOn the date the job interview was created
     * @param time the time the job interview took
     * @param score the score of the job interview
     * @param result the result of the job interview
     * @param application the application the job interview is associated with
     */
    private void registerJobInterview(Calendar createdOn, Integer time, Integer score, String result, Application application) {
        controller.addJobInterview(createdOn, time, score, result, application);
        LOGGER.debug("»»» {}", application);
    }
}


