package core.application.controllers;

import core.domain.application.Application;
import core.domain.client.ClientUser;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.ClientUserRepository;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class ListAllApplicationsofJobOpeningController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final UserManagementService userSvc = AuthzRegistry.userService();

    private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

    //private final JobOpeningService jobserv = AuthzRegistry.jobService();

    public Iterable<ClientUser> activeClientUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.ADMIN);

        return this.repo.findAllActive();
    }

    public Iterable<Application> allApplicationsOfJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);
        List<Application> applicationOfJobOpening = new ArrayList<>();

        for (SystemUser u : userSvc.allUsers()) {
            if (u.hasAny(Jobs4URoles.ADMIN, Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.LANGUAGE_ENGINEER, Jobs4URoles.OPERATOR)) {

                for (Application a : applicationOfJobOpening) {

                }
            }

            return null;
        }
        return null;
    }

    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);
        List<JobOpening> jobOpenings = new ArrayList<>();

        for (SystemUser u : userSvc.allUsers()) {
            if (u.hasAny(Jobs4URoles.ADMIN, Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.LANGUAGE_ENGINEER, Jobs4URoles.OPERATOR)) {

                for (JobOpening j : jobOpenings) {

                }
            }

            return null;
        }
        return null;
    }


    public JobOpening findJobOpening(JobReference jobReference){
         authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);






        return null;
    }



}
