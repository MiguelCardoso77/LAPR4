package core.application.controllers;

import core.domain.application.Application;
import core.domain.email.Email;
import core.domain.jobOpening.JobOpening;

import java.util.List;

/**
 * Controller responsible for notifying candidates of their ranking results for a job opening.
 *
 * @author tomasgoncalves
 * */
public class NotifyResultOfRankCandidatesController {

    private final NotifyCandidatesController notifyCandidatesController = new NotifyCandidatesController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    /**
     * Selects a job opening for notifying candidates of their ranking results.
     *
     * @return The selected job opening.
     */
    public JobOpening selectJobOpening(){
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Retrieves all applications of a job opening that have been accepted.
     *
     * @param jobOpening The job opening to retrieve applications for.
     * @return Iterable of applications that have been accepted for the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpeningAccepted(JobOpening jobOpening){
        return listJobOpeningApplicationsController.allApplicationsOfJobOpeningAccepted(jobOpening);
    }

    /**
     * Finds the email address of a candidate associated with an application.
     *
     * @param application The application of the candidate.
     * @return The email address of the candidate.
     */
    public String findCandidateEmail(Application application){
        return  notifyCandidatesController.findCandidateEmail(application);
    }

    /**
     * Creates an email message.
     *
     * @param candidateEmail The email address of the candidate.
     * @param subject The subject of the email.
     * @param body The body content of the email.
     * @return The created email.
     */
    public Email createEmail(String candidateEmail, String subject, String body){
        return notifyCandidatesController.createEmail(candidateEmail , subject,body);
    }

    /**
     * Sends multiple emails.
     *
     * @param emailsToSend The list of emails to send.
     */
    public void sendEmails(List<Email> emailsToSend) {
        notifyCandidatesController.sendEmails(emailsToSend);
    }

    /**
     * Sends an email to a single recipient.
     *
     * @param emailsToSendCostumer The email to send.
     */
    public void sendEmailCostumer(Email emailsToSendCostumer) {
        notifyCandidatesController.sendEmailCostumer(emailsToSendCostumer);
    }


}
