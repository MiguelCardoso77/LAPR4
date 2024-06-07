package bootstrappers.bootstraping;

import core.application.controllers.CandidateApplicationStateController;
import eapli.framework.actions.Action;

public class NotificationsBootstrapper implements Action {
    private final CandidateApplicationStateController candidateApplicationStateController = new CandidateApplicationStateController();
    @Override
    public boolean execute(){
        candidateApplicationStateController.notifyAcceptedApplications();
        candidateApplicationStateController.notifyDeclinedApplications();
        candidateApplicationStateController.notifyChosenApplications();
        candidateApplicationStateController.notifyReceivedApplications();
        return true;
    }
}
