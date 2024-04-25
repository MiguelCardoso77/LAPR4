package core.application.controllers;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.customer.TelephoneNumber;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import core.services.CandidateService;
import core.services.JobOpeningService;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

import java.util.ArrayList;
import java.util.List;

public class DisplayCandidateDataController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final JobOpeningService jobserv = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();
    private final CandidateService candServ = new CandidateService();


    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Application> allApplications = appServ.allApplication();

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().equals(jobReference)) {
                allApplicationsJobOpening.add(a);
            }
        }
        return allApplicationsJobOpening;
    }


    //pode dar jeito noutras us mas não é para ficar nesta classe porque nao tem nada a ver
    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        return jobserv.allJobOpenings();
    }

    public JobOpening findJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<JobOpening> allJobOpenings = jobserv.allJobOpenings();
        for (JobOpening j : allJobOpenings) {
            if (j.jobReference().equals(jobReference)) {
                return j;
            }
        }
        return null;
    }
    public Candidate candidateOfApplication(Application application) {
        if (application != null) {
            TelephoneNumber telephoneNumber = application.telephoneNumber();
            if (telephoneNumber != null) {
                return candServ.findCandidate(telephoneNumber);
            }
        }
        return null;
    }
    public Iterable<Candidate> allCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);
        return candServ.allCandidates();
    }

    public Application findApplication(JobReference jobReference) {
        return null;
    }

}
