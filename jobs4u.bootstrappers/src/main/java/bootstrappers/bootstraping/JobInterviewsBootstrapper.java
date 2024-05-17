package bootstrappers.bootstraping;

import core.application.controllers.AddJobInterviewController;
import core.application.controllers.SelectInterviewModelController;
import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.services.JobInterviewService;
import eapli.framework.actions.Action;

import java.util.Calendar;
import java.util.List;

public class JobInterviewsBootstrapper implements Action {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final AddJobInterviewController controller = new AddJobInterviewController();
    private final JobInterviewService jobInterviewService = new JobInterviewService();

    @Override
    public boolean execute() {
        Calendar createdOn = Calendar.getInstance();
        List<Application> applications = (List<Application>) applicationRepository.allApplications();

        registerJobInterview(createdOn, 20, 80, "Passed", applications.get(0));
        registerJobInterview(createdOn,20, 30, "Not approved", applications.get(1));
        registerJobInterview(createdOn, 30, 90, "Passed", applications.get(2));
        registerJobInterview(createdOn, 30, 20, "Not approved", applications.get(3));
        registerJobInterview(createdOn, 40, 40, "Not approved", applications.get(4));
        registerJobInterview(createdOn, 40, 50, "Passed", applications.get(5));

        registerJobInterview(createdOn, 20, 80, "Passed", applications.get(0));
        //"jobs4u.core/src/main/resources/interviewModels/interviewModel1.txt"

        return true;
    }

    private void registerJobInterview(Calendar createdOn, Integer time, Integer score, String result, Application application){
        controller.addJobInterview(createdOn, time, score, result, application);
    }
}


