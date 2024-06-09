package bootstrappers.bootstraping;

import core.application.controllers.CandidateApplicationStateController;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsBootstrapper.class);
    private final CandidateApplicationStateController candidateApplicationStateController = new CandidateApplicationStateController();

    @Override
    public boolean execute() {
        createNotifications();
        return true;
    }

    private void createNotifications() {
        candidateApplicationStateController.notifyAcceptedApplications();
        candidateApplicationStateController.notifyDeclinedApplications();
        candidateApplicationStateController.notifyChosenApplications();
        candidateApplicationStateController.notifyReceivedApplications();
        LOGGER.debug("»»» Notifications created");
    }
}
