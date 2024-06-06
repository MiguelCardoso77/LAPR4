package core.application.controllers;

import core.domain.customer.Customer;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobReference;
import core.domain.jobOpening.Mode;
import core.services.JobOpeningService;
import core.domain.user.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddJobOpeningController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    public void addJobOpening(JobReference jobReference, String description, int vacanciesNumber, String address,
                              Mode mode, ContractType contractType, String titleOrFunction, Customer customer) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);

        jobOpeningService.registerJobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, customer);
    }

    public boolean verifyID(JobReference jobReference) {
        return jobOpeningService.verifyJobReference(jobReference);
    }
}
