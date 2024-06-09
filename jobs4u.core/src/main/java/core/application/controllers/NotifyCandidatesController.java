package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.email.Email;
import core.services.ApplicationService;
import core.services.EmailService;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller for notifying candidates in the Jobs4U system.
 * This class provides methods to find applications to notify, find a candidate's email,
 * check an application's status, create an email, send emails, and send an email to a customer.
 * It uses the ApplicationService and EmailService from the core services.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class NotifyCandidatesController {
    private final ApplicationService applicationService = new ApplicationService();
    private final EmailService emailService = new EmailService();
    /**
     * Finds the applications to notify.
     *
     * @return a list of the applications to notify
     */
    public List<Application> findApplicationsToNotify() {
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if (application.status() == Status.ACCEPTED || application.status() == Status.DECLINED) {
                appsToNotify.add(application);
            }
        }

        return appsToNotify;
    }
    /**
     * Finds a candidate's email.
     *
     * @param application the application of the candidate
     * @return the email of the candidate
     */
    public String findCandidateEmail(Application application) {
        return application.candidate().user().email().toString();
    }
    /**
     * Checks an application's status.
     *
     * @param application the application to check the status of
     * @return the status of the application
     */
    public String checkApplicationStatus(Application application) {
        return application.status().toString();
    }
    /**
     * Creates an email.
     *
     * @param candidateEmail the email of the candidate
     * @param subject the subject of the email
     * @param body the body of the email
     * @return the created email
     */
    public Email createEmail(String candidateEmail, String subject, String body) {
        return emailService.createEmail(candidateEmail, subject, body);
    }
    /**
     * Sends emails.
     *
     * @param emailsToSend the emails to send
     */
    public void sendEmails(List<Email> emailsToSend) {
        emailService.sendAllEmails(emailsToSend);
    }
}