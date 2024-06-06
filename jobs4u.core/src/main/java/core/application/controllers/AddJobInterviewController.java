package core.application.controllers;

import core.domain.application.Application;
import core.domain.user.Jobs4URoles;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

@UseCaseController
public class AddJobInterviewController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobInterviewService jobInterviewService = new JobInterviewService();

    public void addJobInterview(Calendar createdOn, Integer time, Integer score, String result,
                                Application application){

        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);

        jobInterviewService.registerJobInterview(createdOn, time, score, result, application);
    }
}
