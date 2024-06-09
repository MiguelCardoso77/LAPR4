package bootstrappers.bootstraping;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecificationBuilder;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps job requirements specifications.
 * This bootstrapper registers job requirement specifications using the JobRequirementsSpecificationBuilder and saves them to the repository.
 * Requires access to the JobRequirementsSpecificationRepository and JobRequirementsSpecificationBuilder.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Diogo Ribeiro
 */
public class RequirementsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequirementsBootstrapper.class);

    private final JobRequirementsSpecificationRepository repository = PersistenceContext.repositories().jobRequirements();
    private final JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();

    /**
     * Executes the bootstrapping process for registering job requirement specifications.
     * Registers job requirement specifications using the JobRequirementsSpecificationBuilder and saves them to the repository.
     * @return true if bootstrapping is successful, false otherwise
     */
    @Override
    public boolean execute() {
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements1.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements2.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements3.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements4.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/templates/requirements/requirements5.txt");

        return true;
    }

    /**
     * Registers a job requirement specification using the JobRequirementsSpecificationBuilder and saves it to the repository.
     * @param path the path to the file containing the job requirement specification
     */
    private void registerJobRequirementSpecification(String path) {
        JobRequirementsSpecification jobRequirementsSpecification = builder.withoutId(path).build();
        repository.save(jobRequirementsSpecification);
        LOGGER.debug("»»» Requirements : {}", path);
    }
}
