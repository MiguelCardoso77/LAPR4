package core.application.controllers;

import core.domain.application.Application;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class VerificationRequirementsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ApplicationService appServ = new ApplicationService();



    public Application findApplicationById(Application applicationID) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Application> allApplications = appServ.allApplications();
        for (Application application : allApplications) {
            if (application.identity() == applicationID.identity() ) {
                return application;
            }
        }
        return null;
    }



}
