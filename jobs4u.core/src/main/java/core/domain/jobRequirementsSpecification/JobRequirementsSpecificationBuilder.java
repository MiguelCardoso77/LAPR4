package core.domain.jobRequirementsSpecification;


import core.domain.jobOpening.JobOpening;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobRequirementsSpecificationBuilder {

    private static final Logger LOGGER = LogManager.getLogger(JobOpening.class);
    private Integer idRequirements;
    private Requirements requirements;


    public JobRequirementsSpecificationBuilder withAll(Requirements requirements, Integer idRequirements) {
        this.requirements = requirements;
        this.idRequirements = idRequirements;
        return this;
    }


    public JobRequirementsSpecification build() {
        JobRequirementsSpecification jobRequirementsSpecification;

        if (idRequirements == null || requirements == null) {
            LOGGER.error("Missing mandatory information to build a JobRequirementsSpecification");
            return null;
        } else {
            LOGGER.debug("Building JobRequirementSpecification with requirements {}, idRequirements {}", requirements, idRequirements);
            jobRequirementsSpecification = new JobRequirementsSpecification(requirements, idRequirements);
        }

        return jobRequirementsSpecification;
    }


}
