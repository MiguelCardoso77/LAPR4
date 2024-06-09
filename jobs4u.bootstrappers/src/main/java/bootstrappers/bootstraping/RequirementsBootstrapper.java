package bootstrappers.bootstraping;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecificationBuilder;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequirementsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequirementsBootstrapper.class);

    private final JobRequirementsSpecificationRepository repository = PersistenceContext.repositories().jobRequirements();
    private final JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();

    @Override
    public boolean execute() {
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements1.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements2.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements3.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements4.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements5.txt");

        return true;
    }

    private void registerJobRequirementSpecification(String path) {
        JobRequirementsSpecification jobRequirementsSpecification = builder.withoutId(path).build();
        repository.save(jobRequirementsSpecification);
        LOGGER.debug("»»» Requirements : {}", path);
    }
}
