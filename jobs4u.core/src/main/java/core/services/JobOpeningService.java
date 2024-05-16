package core.services;

import core.domain.company.Company;
import core.domain.jobOpening.*;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.repositories.JobOpeningRepository;
import core.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing job openings.
 *
 * @author 1220812@isep.ipp.pt
 */

@Service
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    /**
     * Registers a new job opening.
     *
     * @param jobReference    The reference of the job opening.
     * @param description     The description of the job opening.
     * @param vacanciesNumber The number of vacancies for the job opening.
     * @param address         The address of the job opening.
     * @param mode            The mode of the job opening.
     * @param contractType    The contract type of the job opening.
     * @param titleOrFunction The title or function of the job opening.
     * @param company         The company offering the job opening.
     * @return The registered job opening.
     */
    @Transactional
    public JobOpening registerJobOpening(JobReference jobReference, String description, int vacanciesNumber, String address,
                                         Mode mode, ContractType contractType, String titleOrFunction, Company company) {
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        jobOpeningBuilder.withAll(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company, null);
        JobOpening jobOpening = jobOpeningBuilder.build();
        return jobOpeningRepository.save(jobOpening);
    }
    /**
     * Finds a job opening by its reference.
     *
     * @param jobReference The reference of the job opening to find.
     * @return The found job opening, or null if not found.
     */
    public JobOpening findJobOpening(JobReference jobReference){
        Iterable<JobOpening> jobOpenings = jobOpeningRepository.allJobOpenings();
        for (JobOpening jobOpening : jobOpenings) {
            if(jobOpening.identity().equals(jobReference)){
                return jobOpening;
            }
        }
        return null;
    }
    /**
     * Updates the job requirements specification for the job opening.
     *
     * @param jobReference               The reference of the job opening.
     * @param jobRequirementsSpecification The new job requirements specification.
     * @return JobOpening if the update was successful, null otherwise.
     */
    @Transactional
    public JobOpening updateJobRequirements(JobReference jobReference, JobRequirementsSpecification jobRequirementsSpecification) {
        JobOpening jobOpening = jobOpeningRepository.ofIdentity(jobReference).orElse(null);
        if (jobOpening != null) {
            jobOpening.updateJobRequirements(jobRequirementsSpecification);
            jobOpeningRepository.save(jobOpening);
            return jobOpening;
        }
        return null;
    }
    /**
     * Verifies if a job opening with the given reference exists.
     *
     * @param jobReference The reference of the job opening to verify.
     * @return True if the job opening exists, false otherwise.
     */
    public boolean verifyJobReference(JobReference jobReference){
        if(jobOpeningRepository.ofIdentity(jobReference).isPresent()){
            return true;
        }
        return false;
    }
    /**
     * Retrieves all job openings.
     *
     * @return An Iterable containing all job openings.
     */
    public Iterable<JobOpening> allJobOpenings() {
        return jobOpeningRepository.findAll();
    }

}