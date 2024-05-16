package core.application.controllers;

import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.process.Process;
import core.domain.user.Jobs4URoles;
import core.services.JobOpeningService;
import core.services.ProcessService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

public class ListProcessJobOpeningController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final JobOpeningService jobserv = new JobOpeningService();
    private final ProcessService processService = new ProcessService();


    public Iterable<Process> allProcessJobOpening(JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Process> allProcesses = processService.allProcesses();

        List<Process> allProcessJobOpening = new ArrayList<>();
        for (Process process : allProcesses) {
            if (process.jobReference().sameReference(jobOpening.jobReference())) {
                allProcessJobOpening.add(process);
            }
        }
        return allProcessJobOpening;
    }

    public Process findProcessById(Integer identity) {
        Iterable<Process> allProcesses = processService.allProcesses();

        for (Process process : allProcesses){
            if (process.identity().equals(identity)){
                return process;
            }
        }
        return null;
    }

}
