package backoffice.presentation.candidate;

import core.application.controllers.NotifyCandidatesController;
import core.domain.application.Application;
import core.domain.email.Email;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class NotifyCandidatesUI extends AbstractUI {
    private final NotifyCandidatesController theController = new NotifyCandidatesController();

    @Override
    protected boolean doShow() {
        String loggedEmail = theController.getLoggedInUserEmail();
        List<Application> applications = theController.findApplicationsToNotify();
        System.out.println("Candidates that will be notified: " + applications.size());

        for (Application application : applications) {
            String candidateEmail = theController.findCandidateEmail(application);
            String status = theController.checkApplicationStatus(application);
            String subject = buildSubject(application);
            String body = buildBody(application, status);

            theController.createEmail(loggedEmail, candidateEmail, subject, body);
        }

        theController.closeSocket();

        return true;
    }

    private String buildSubject(Application application) {
        return "Verification Process - " + application.dataFile();
    }

    private String buildBody(Application application, String status) {
        return "Dear candidate, the status of your application '" + application.dataFile() + "' has changed to '" + status + "'.\n"
                + "Please check the application for more details.";
    }

    @Override
    public String headline() {
        return "Notify Candidates -> Verification Process";
    }
}
