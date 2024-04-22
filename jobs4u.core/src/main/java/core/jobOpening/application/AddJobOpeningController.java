package core.jobOpening.application;

import core.jobOpening.domain.ContractType;
import core.jobOpening.domain.JobOpening;
import core.jobOpening.domain.JobReference;
import core.jobOpening.domain.Mode;
import core.jobOpening.services.JobOpeningService;
import core.usermanagement.domain.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddJobOpeningController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    public int buildJobReference(int companyNumber) {
        JobReference jobReference = new JobReference(companyNumber);
        return jobReference.buildJobReference(companyNumber);
    }

    public JobOpening addJobOpening(int jobReference, String description, int vacanciesNumber, String adress, Mode mode, ContractType contractType, String titleOrFunction) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.CUSTOMER_MANAGER);

        return jobOpeningService.registerJobOpening(jobReference, description, vacanciesNumber, adress, mode, contractType, titleOrFunction);
    }
}
