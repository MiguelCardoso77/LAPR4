package backoffice.presentation.candidate;

import core.application.controllers.NotifyCandidatesController;
import core.domain.application.Application;
import core.domain.email.Email;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User interface responsible for notifying candidates about the verification process.
 * It retrieves applications to be notified from the {@link NotifyCandidatesController} and constructs emails
 * to be sent to candidates.
 * Upon execution, it sends notification emails to candidates and returns true to indicate the successful completion
 * of the notification process.
 *
 * @author Miguel Cardoso
 */
public class NotifyCandidatesUI extends AbstractUI {
    private final NotifyCandidatesController theController = new NotifyCandidatesController();

    /**
     * Displays the UI for notifying candidates about the verification process.
     * It retrieves applications to be notified, constructs email messages, and sends them to candidates.
     *
     * @return true indicating the successful completion of the notification process.
     */
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

    /**
     * Builds the subject for the notification email.
     *
     * @param application the application for which the notification is being sent.
     * @return the constructed subject for the email.
     */
    private String buildSubject(Application application) {
        return "Verification Process - " + application.dataFile();
    }

    /**
     * Builds the body of the notification email.
     *
     * @param application the application for which the notification is being sent.
     * @param status the status of the application.
     * @return the constructed body for the email.
     */
    private String buildBody(Application application, String status) {
        return  "Dear Candidate,\n\n" +
                "We hope this message finds you well.\n\n" +
                "We are writing to inform you that the status of your application" + application.dataFile() + "has been changed to: " + status + ".\n\n" +
                "If you have any questions or require further assistance, please do not hesitate to contact us.\n\n" +
                "Best regards,\n" +
                "Jobs4U\n";
    }

    /**
     * Provides the headline for the UI.
     *
     * @return the headline for the UI.
     */

    @Override
    public String headline() {
        return "Notify Candidates -> Verification Process";
    }
}
