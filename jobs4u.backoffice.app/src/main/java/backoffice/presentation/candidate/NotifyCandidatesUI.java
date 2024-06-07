package backoffice.presentation.candidate;

import core.application.controllers.NotifyCandidatesController;
import core.domain.application.Application;
import core.domain.email.Email;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class NotifyCandidatesUI extends AbstractUI {
    private final NotifyCandidatesController theController = new NotifyCandidatesController();

    @Override
    protected boolean doShow() {
        List<Application> applications = theController.findApplicationsToNotify();
        List<Email> emailsToSend = new ArrayList<>();
        System.out.println("Candidates that will be notified: " + applications.size());

        for (Application application : applications) {
            String candidateEmail = theController.findCandidateEmail(application);
            String status = theController.checkApplicationStatus(application);
            String subject = buildSubject(application);
            String body = buildBody(application, status);

            Email emailObj = theController.createEmail(candidateEmail, subject, body);
            emailsToSend.add(emailObj);
        }

        theController.sendEmails(emailsToSend);

        return true;
    }

    private String buildSubject(Application application) {
        return "Verification Process - " + application.dataFile();
    }

    private String buildBody(Application application, String status) {
        return  "Dear Candidate,\n\n" +
                "We hope this message finds you well.\n\n" +
                "We are writing to inform you that the status of your application" + application.dataFile() + "has been changed to: " + status + ".\n\n" +
                "If you have any questions or require further assistance, please do not hesitate to contact us.\n\n" +
                "Best regards,\n" +
                "Jobs4U\n";
    }

    @Override
    public String headline() {
        return "Notify Candidates -> Verification Process";
    }
}
