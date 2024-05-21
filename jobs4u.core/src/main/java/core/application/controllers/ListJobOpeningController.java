package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListJobOpeningController {
    private final JobOpeningService jobserv = new JobOpeningService();

    public Iterable<JobOpening> showJobOpenings() {
        Iterable<JobOpening> iterable = allJobOpening();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings: ");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
        }
        return iterable;
    }

    public Iterable<JobOpening> allJobOpening() {
        return jobserv.allJobOpenings();
    }

    public JobOpening findJobOpeningByJobReference(JobReference jobReference){
        return jobserv.findJobOpening(jobReference);
    }

}
