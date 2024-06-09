package core.services;

import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningDTO;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle operations related to job openings for customers.
 * This service provides functionality to list all job openings for a specific customer.
 *
 * @author Diogo Ribeiro
 */
public class JobOpeningDTOService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final ApplicationService applicationService = new ApplicationService();

    /**
     * Converts a JobOpening entity to a JobOpeningDTO.
     *
     * @param jobOpening The JobOpening entity to convert.
     * @return The converted JobOpeningDTO.
     */
    public JobOpeningDTO toDTO(JobOpening jobOpening) {

        int numberOfApplicants = applicationService.numberOfApplicationsForJobOpening(jobOpening);

        return new JobOpeningDTO(
                jobOpening.jobReference().toString(),
                jobOpening.titleOrFunction().toString(),
                jobOpening.process().processDate().getTime(),
                numberOfApplicants
        );
    }

    /**
     * Retrieves all job openings for a specific customer.
     *
     * @param customer The customer whose job openings are to be retrieved.
     * @return A list of JobOpeningDTOs representing the job openings for the specified customer.
     */
    public List<JobOpeningDTO> allCustomersJobOpenings(Customer customer){
        List<JobOpening> allJobOpenings = (List<JobOpening>) jobOpeningRepository.allJobOpenings();
        List<JobOpeningDTO> jobOpeningsDTO = new ArrayList<>();

        for(JobOpening jobOpening : allJobOpenings){
            if(jobOpening.customer().equals(customer)){
                jobOpeningsDTO.add(toDTO(jobOpening));
            }
        }
        return jobOpeningsDTO;
    }

    /**
     * Converts a list of JobOpening entities to a list of JobOpeningDTOs.
     *
     * @param jobOpenings The list of JobOpening entities to convert.
     * @return The list of converted JobOpeningDTOs.
     */
    public List<JobOpeningDTO> jobOpeningDTOS(List<JobOpening> jobOpenings) {
        List<JobOpeningDTO> jobOpeningDTOs = new ArrayList<>();
        for (JobOpening jobOpening : jobOpenings) {
            JobOpeningDTO jobOpeningDTO = toDTO(jobOpening);
            jobOpeningDTOs.add(jobOpeningDTO);
        }
        return jobOpeningDTOs;
    }
}
