package bootstrappers.bootstraping;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;

public class AddRequirementsBootstrapper implements Action {
    final JobRequirementsSpecificationRepository repository = PersistenceContext.repositories().jobRequirements();
    @Override
    public boolean execute() {
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements1.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements2.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements3.txt");
        registerJobRequirementSpecification("jobs4u.core/src/main/resources/requirements/requirements4.txt");

        return true;
    }

    private void registerJobRequirementSpecification(String path) {
        JobRequirementsSpecification jobRequirementsSpecification = new JobRequirementsSpecification(path);
        repository.save(jobRequirementsSpecification);
    }
}
