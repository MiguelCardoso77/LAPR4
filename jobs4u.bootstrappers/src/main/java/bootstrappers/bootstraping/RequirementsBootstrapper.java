package bootstrappers.bootstraping;

import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;

public class RequirementsBootstrapper implements Action {
    JobRequirementsSpecificationRepository jobsRequirementsRepository = PersistenceContext.repositories().jobRequirements();

    @Override
    public boolean execute() {


        return true;
    }
}
