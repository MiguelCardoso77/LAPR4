package bootstrappers.bootstraping;

import core.application.controllers.AddProcessController;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProcessBootstrapper implements Action {

    @Override
    public boolean execute() {

        return true;
    }
}
