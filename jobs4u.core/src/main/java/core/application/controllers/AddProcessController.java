package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.user.Jobs4URoles;
import core.services.ProcessService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AddProcessController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProcessService processService = new ProcessService();

    public Process registerProcess(ProcessState processState, JobOpening jobReference){
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);

        return processService.registerProcess(processState, jobReference);
    }


}
