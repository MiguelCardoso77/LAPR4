package core.services;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecificationBuilder;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
/**
 *
 * @author 1220812@isep.ipp.pt
 *
 */
@Service
public class JobRequirementsService {

    private final JobRequirementsSpecificationRepository jobRequirementsSpecificationRepository = PersistenceContext.repositories().jobRequirements();
    /**
     * Registers a new job requirement specification.
     *
     * @param jobRequirementsPath The path for the job requirements specification file.
     * @return The registered job requirements specification.
     */
    @Transactional
    public JobRequirementsSpecification registerJobRequirement(String jobRequirementsPath) {
        JobRequirementsSpecificationBuilder jobRequirementsSpecificationBuilder= new JobRequirementsSpecificationBuilder();
        jobRequirementsSpecificationBuilder.withoutId(jobRequirementsPath);
        JobRequirementsSpecification jobRequirementsSpecification = jobRequirementsSpecificationBuilder.build();
        return jobRequirementsSpecificationRepository.save(jobRequirementsSpecification);
    }

    /**
     * Retrieves all job requirements specifications.
     *
     * @return Iterable containing all job requirements specifications.
     */
    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return jobRequirementsSpecificationRepository.allJobRequirementsSpecification();
    }
    /**
     * Finds a job requirements specification by its ID.
     *
     * @param id The ID of the job requirements specification to find.
     * @return The found job requirements specification, or null if not found.
     */
    public JobRequirementsSpecification findJobRequirementsSpecification(Integer id){
        Iterable<JobRequirementsSpecification> jobRequirementsSpecifications = jobRequirementsSpecificationRepository.allJobRequirementsSpecification();
        for (JobRequirementsSpecification jobRequirementsSpecification : jobRequirementsSpecifications) {
            if(id.equals(jobRequirementsSpecification.identity())){
                return jobRequirementsSpecification;
            }
        }
        return null;
    }
    /**
     * Verifies if a job requirements specification exists.
     *
     * @param jobRequirementsSpecification The job requirements specification to verify.
     * @return True if the job requirements specification exists, otherwise false.
     */
    public boolean verifyJobRequirementsSpecification(Integer jobRequirementsSpecification){
        if(jobRequirementsSpecificationRepository.ofIdentity(jobRequirementsSpecification).isPresent()){
            return true;
        }
        return false;
    }

}
