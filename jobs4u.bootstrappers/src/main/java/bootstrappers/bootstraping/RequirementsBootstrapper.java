package bootstrappers.bootstraping;

import core.application.controllers.ListJobRequirementsSpecificationController;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.persistence.PersistenceContext;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;

public class RequirementsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsBootstrapper.class);
    final ListJobRequirementsSpecificationController controller = new ListJobRequirementsSpecificationController();

    private final JobRequirementsSpecificationRepository repository = PersistenceContext.repositories().jobRequirements();
    @Override
    public boolean execute() {


        return true;
    }
/*
    private void registerJobRequirementSpecification(String path) throws FileNotFoundException {
        Path path1 = Path.of(path);
        List<String> data = controller.importRequirementsSpecification(path1);
        JobRequirementsSpecification jobRequirementsSpecification = controller.extractSpecificationFromFile(path);
        LOGGER.debug("»»» %s", jobRequirementsSpecification);
    }

 */
}
