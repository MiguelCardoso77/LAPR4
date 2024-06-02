package bootstrappers.bootstraping;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecificationBuilder;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;

public class RequirementsBootstrapper implements Action {
    final JobRequirementsSpecificationRepository repository = PersistenceContext.repositories().jobRequirements();
    final JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
    @Override
    public boolean execute() {
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements1.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements2.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements3.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements5.txt");

        return true;
    }

    private void registerJobRequirementSpecification(String path) {
        JobRequirementsSpecification jobRequirementsSpecification = builder.withoutId(path).build();
        repository.save(jobRequirementsSpecification);
    }
}
