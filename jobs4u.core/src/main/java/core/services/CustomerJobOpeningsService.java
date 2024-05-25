package core.services;

import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningDTO;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;

import java.util.List;

public class CustomerJobOpeningsService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    /**
    private JobOpeningDTO toDTO(JobOpening jobOpening) {
        return new JobOpeningDTO(
                jobOpening.jobReference().toString(),
                jobOpening.titleOrFunction().toString(),
                jobOpening.activeSince().getTime(),
                jobOpening.process().numberOfApplicants() // ir buscar o number of applicants
        );
    }
    public List<JobOpeningDTO> allCustomersJobOpenings(Customer customer){


    }
     */
}
