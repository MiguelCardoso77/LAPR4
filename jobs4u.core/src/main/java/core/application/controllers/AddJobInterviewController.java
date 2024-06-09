package core.application.controllers;

import core.domain.application.Application;
import core.domain.user.Jobs4URoles;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;

/**
 * Controller for managing the addition of job interviews in the Jobs4U system.
 * This class provides a method to add a job interview.
 * It uses the AuthorizationService and JobInterviewService from the eapli framework.
 *
 * @author Diana Neves
 */
@UseCaseController
public class AddJobInterviewController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobInterviewService jobInterviewService = new JobInterviewService();

    /**
     * Adds a job interview with the specified details.
     *
     * @param createdOn   the date when the job interview was created
     * @param time        the duration of the job interview
     * @param score       the score of the job interview
     * @param result      the result of the job interview
     * @param application the application associated with the job interview
     */
    public void addJobInterview(Calendar createdOn, Integer time, Integer score, String result, Application application) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);

        jobInterviewService.registerJobInterview(createdOn, time, score, result, application);
    }
}
