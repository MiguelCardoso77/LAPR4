package core.services;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecificationBuilder;
import core.domain.jobRequirementsSpecification.Requirements;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class JobRequirementsService {

    private final JobRequirementsSpecificationRepository jobRequirementsSpecificationRepository = PersistenceContext.repositories().jobRequirements();

    @Transactional
    public JobRequirementsSpecification registerJobRequirement(Integer idJobRequirement , Requirements requirements) {
        JobRequirementsSpecificationBuilder jobRequirementsSpecificationBuilder= new JobRequirementsSpecificationBuilder();
        jobRequirementsSpecificationBuilder.withAll(requirements , idJobRequirement);
        JobRequirementsSpecification jobRequirementsSpecification = jobRequirementsSpecificationBuilder.build();
        return jobRequirementsSpecificationRepository.save(jobRequirementsSpecification);
    }

    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return jobRequirementsSpecificationRepository.allJobRequirementsSpecification();
    }


}
