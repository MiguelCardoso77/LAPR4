package core.application.controllers;

import core.services.CustomerJobOpeningsService;
import eapli.framework.application.UseCaseController;

import java.util.List;

/**
 * Controller to handle the use case of listing all job openings for a specific customer.
 * This class coordinates the interaction between the UI and the service layer.
 *
 * @author Diogo Ribeiro
 */
@UseCaseController
public class ListCustomerJobOpeningsController {
    private final CustomerJobOpeningsService customerAppService = new CustomerJobOpeningsService();

    /**
     * Sends a request to the server to retrieve all job openings for a specific customer.
     *
     * @param email The email of the customer.
     * @return A list of JobOpeningDTO objects representing the job openings for the customer.
     */
    public List<String> sendCustomerJobOpenings(String email) {
        return customerAppService.requestJobOpenings(email);
    }
}
