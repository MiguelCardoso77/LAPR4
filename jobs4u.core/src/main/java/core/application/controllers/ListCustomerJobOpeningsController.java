package core.application.controllers;

import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpeningDTO;
import core.services.CustomerJobOpeningsService;
import eapli.framework.application.UseCaseController;

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

    /**
     * Retrieves all job openings for a given customer.
     *
     * @param customer The customer whose job openings are to be retrieved.
     * @return A list of JobOpeningDTOs representing the job openings for the specified customer.
     */
    public List<JobOpeningDTO> customerJobOpenings(Customer customer){
        return customerJobOpeningsService.allCustomersJobOpenings(customer);
    }
}
