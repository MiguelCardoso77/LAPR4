package core.application.controllers;

import core.domain.application.Application;
import core.domain.client.ClientUser;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.ClientUserRepository;
import core.services.ApplicationService;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

import java.util.ArrayList;
import java.util.List;

public class ListJobOpeningApplicationsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final UserManagementService userSvc = AuthzRegistry.userService();

    private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

    private final JobOpeningService jobserv = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();


    public Iterable<ClientUser> activeClientUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.ADMIN);

        return this.repo.findAllActive();
    }

    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Application> allApplications = appServ.allApplication();

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().equals(jobReference)) {
                allApplicationsJobOpening.add(a);
            }
        }
        return  allApplicationsJobOpening;
    }


    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);
        return jobserv.allJobOpenings();
    }


    public JobOpening findJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<JobOpening> allJobOpenings = jobserv.allJobOpenings();
        for (JobOpening j : allJobOpenings) {
            if (j.jobReference().equals(jobReference)) {
                return j;
            }
        }
        return null;
    }


}