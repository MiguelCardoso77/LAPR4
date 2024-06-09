package core.services;

import core.domain.customer.Customer;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.*;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.repositories.JobOpeningRepository;
import core.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing job openings.
 *
 * @author Diogo Ribeiro
 */
@Service
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final ProcessService processService = new ProcessService();

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
     * @param customer         The customer associated to the job opening.
     * @return The registered job opening.
     */
    @Transactional
    public JobOpening registerJobOpening(JobReference jobReference, String description, int vacanciesNumber, String address,
                                         Mode mode, ContractType contractType, String titleOrFunction, Customer customer) {
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        Process process = processService.registerProcess(ProcessState.APPLICATION);
        jobOpeningBuilder.withAll(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, customer, null, process, null);
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
     * Updates the interview model for the job opening.
     *
     * @param jobReference               The reference of the job opening.
     * @param interviewModel             The new interview model.
     * @return JobOpening if the update was successful, null otherwise.
     */
    @Transactional
    public JobOpening updateInterviewModel(JobReference jobReference, InterviewModel interviewModel){
        JobOpening jobOpening = jobOpeningRepository.ofIdentity(jobReference).orElse(null);
        if(jobOpening != null){
            jobOpening.updateInterviewModel(interviewModel);
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
        return jobOpeningRepository.ofIdentity(jobReference).isPresent();
    }

    /**
     * Retrieves a list off all the job openings associated with one customer
     *
     * @param customer The customer to verify
     * @return list of the job openings associated to the customer
     */
    public List<JobOpening> findByCustomer(Customer customer){
        Iterable<JobOpening> jobOpenings = allJobOpenings();

        List<JobOpening> customerJobOpenings = new ArrayList<>();
        for(JobOpening jobOpening : jobOpenings){
            if(jobOpening.customer().identity().equals(customer.identity())){
                customerJobOpenings.add(jobOpening);
            }
        }
        return customerJobOpenings;
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