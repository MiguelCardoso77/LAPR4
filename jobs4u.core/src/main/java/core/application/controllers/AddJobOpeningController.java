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

/**
 * Controller for managing the addition of job openings in the Jobs4U system.
 * This class provides methods to add a job opening and to verify a job reference.
 * It uses the AuthorizationService and JobOpeningService from the eapli framework.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class AddJobOpeningController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    /**
     * Adds a job opening with the specified details.
     *
     * @param jobReference    the reference of the job opening
     * @param description     the description of the job opening
     * @param vacanciesNumber the number of vacancies for the job opening
     * @param address         the address of the job opening
     * @param mode            the mode of the job opening
     * @param contractType    the contract type of the job opening
     * @param titleOrFunction the title or function of the job opening
     * @param customer        the customer associated with the job opening
     */
    public void addJobOpening(JobReference jobReference, String description, int vacanciesNumber, String address,
                              Mode mode, ContractType contractType, String titleOrFunction, Customer customer) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);

        jobOpeningService.registerJobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, customer);
    }

    /**
     * Verifies a job reference.
     *
     * @param jobReference the reference of the job to verify
     * @return true if the job reference is valid, false otherwise
     */
    public boolean verifyID(JobReference jobReference) {
        return jobOpeningService.verifyJobReference(jobReference);
    }
}
