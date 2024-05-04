package bootstrappers.bootstraping;

import core.application.controllers.AddJobInterviewController;
import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import eapli.framework.actions.Action;

import java.util.Calendar;
import java.util.List;

public class JobInterviewsBootstrapper implements Action {

    final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    final AddJobInterviewController controller = new AddJobInterviewController();

    @Override
    public boolean execute() {

        Calendar createdOn = Calendar.getInstance();
        List<Application> applications = (List<Application>) applicationRepository.allApplications();
        Application application = applications.get(0);
        int time = 140;
        int score = 20;
        String result = "well done";

        registerJobInterview(createdOn,time, score, result, application);

        return true;
    }

    private void registerJobInterview(Calendar createdOn, Integer time, Integer score, String result,
                                      Application application){
        controller.addJobInterview(createdOn, time, score, result, application);
    }
}


