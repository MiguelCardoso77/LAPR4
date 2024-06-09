package core.services;

import core.domain.application.Application;
import core.domain.application.ApplicationsDTO;

/**
 * Service class for converting Application objects to ApplicationsDTO objects.
 * This service relies on the ApplicationService to retrieve necessary information
 * for creating the DTOs.
 * This class provides a method to convert an Application to its corresponding
 * data transfer object (DTO) representation, including the number of applicants for
 * the job opening associated with the application.
 *
 * @author Diana Neves
 */
public class ApplicationsDTOService {
    private final ApplicationService applicationService = new ApplicationService();

    /**
     * Converts an Application to an ApplicationsDTO.
     * This method uses the ApplicationService to determine the number of
     * applicants for the job opening associated with the application.
     *
     * @param application the application to convert
     * @return the corresponding ApplicationsDTO object
     */
    public ApplicationsDTO toDTO(Application application) {
        int numberOfApplicants = applicationService.numberOfApplicationsForJobOpening(application.jobReference());

        return new ApplicationsDTO(application.toStringServer(), numberOfApplicants);
    }
}
