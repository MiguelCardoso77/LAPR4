package bootstrappers.bootstraping;

import core.application.controllers.CandidateApplicationStateController;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps notifications.
 * This bootstrapper creates notifications using the CandidateApplicationStateController.
 * Requires the CandidateApplicationStateController to execute.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Diogo Ribeiro
 */
public class NotificationsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsBootstrapper.class);
    private final CandidateApplicationStateController candidateApplicationStateController = new CandidateApplicationStateController();

    /**
     * Executes the bootstrapping process for creating notifications.
     * Creates notifications using the CandidateApplicationStateController.
     * @return true if bootstrapping is successful, false otherwise
     */
    @Override
    public boolean execute() {
        createNotifications();
        return true;
    }

    /**
     * Creates notifications using the CandidateApplicationStateController.
     */
    private void createNotifications() {
        candidateApplicationStateController.notifyAcceptedApplications();
        candidateApplicationStateController.notifyDeclinedApplications();
        candidateApplicationStateController.notifyChosenApplications();
        candidateApplicationStateController.notifyReceivedApplications();
        LOGGER.debug("»»» Notifications created");
    }
}
