package core.application.controllers;

import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpeningDTO;
import core.services.CustomerApplicationService;
import core.services.CustomerJobOpeningsService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
/**
 * Controller to handle the use case of listing all job openings for a specific customer.
 * This class coordinates the interaction between the UI and the service layer.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class ListCustomerJobOpeningsController {
    private final CustomerJobOpeningsService customerJobOpeningsService = new CustomerJobOpeningsService();
    private final CustomerApplicationService customerAppService = new CustomerApplicationService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    /**
    public void sendCustomerJobOpenings(String email){
        customerAppService.requestJobOpenings(email);
    }
    */
}
