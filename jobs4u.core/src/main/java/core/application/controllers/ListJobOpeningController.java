package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListJobOpeningController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    private final JobOpeningService jobserv = new JobOpeningService();


    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        return jobserv.allJobOpenings();
    }

    public JobOpening findJobOpeningByJobReference(JobReference jobReference){
        return jobserv.findJobOpening(jobReference);
    }

}
