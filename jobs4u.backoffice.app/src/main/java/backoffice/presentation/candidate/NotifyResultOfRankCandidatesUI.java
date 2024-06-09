package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.application.Rank;
import core.domain.application.Status;
import core.domain.candidate.Candidate;
import core.domain.email.Email;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User interface for notifying candidates of their ranking results for a job opening.
 * This UI class facilitates the process of notifying candidates about their application's ranking and status.
 * It interacts with the {@link NotifyResultOfRankCandidatesController} to retrieve necessary data and send emails.
 * After selecting a job opening and fetching the applications accepted for it, it constructs and sends notification emails to both candidates and the respective customer.
 * It provides methods to build the email subjects and bodies for candidates and the customer.
 *
 * @author Tomás Gonçalves
 */
public class NotifyResultOfRankCandidatesUI extends AbstractUI {
    private final NotifyResultOfRankCandidatesController notifyResultOfRankCandidatesController = new NotifyResultOfRankCandidatesController();

    /**
     * Displays the UI for notifying candidates of their ranking results.
     * It selects a job opening, retrieves the accepted applications, constructs notification emails for candidates and the customer, and sends them.
     *
     * @return true indicating the successful execution of the notification process.
     */
    @Override
    protected boolean doShow() {

        List<Email> emailsToSend = new ArrayList<>();

        List<String> emailToCostumer = new ArrayList<>();

        JobOpening jobOpening = notifyResultOfRankCandidatesController.selectJobOpening();

        JobReference jobReference = jobOpening.jobReference();

        Iterable<Application> appToNotify = notifyResultOfRankCandidatesController.allApplicationsOfJobOpeningAccepted(jobOpening);

        if (appToNotify == null) {
            System.out.println(ConsoleColors.RED + "There are no applications accepted for this job opening." + ConsoleColors.RESET);
        } else {
            for (Application application : appToNotify) {

                String candidateEmail = notifyResultOfRankCandidatesController.findCandidateEmail(application);
                String subject = buildSubject(application);
                String body = buildBody1(application, application.rank(), application.status());
                String body2 = buildBody2(application.candidate());
                Email emailObj = notifyResultOfRankCandidatesController.createEmail(candidateEmail, subject, body);
                emailToCostumer.add(body2);
                emailsToSend.add(emailObj);
            }

            String costumerEmail = jobOpening.customer().identity().toString();
            String subject1 = buildSubject1(jobReference);
            String body3 = emailToCostumer.toString();

            Email emailCostumer = notifyResultOfRankCandidatesController.createEmail(costumerEmail, subject1, body3);

            notifyResultOfRankCandidatesController.sendEmails(emailsToSend);

            notifyResultOfRankCandidatesController.sendEmailCostumer(emailCostumer);
        }
        return true;
    }

    /**
     * Returns the headline for the UI.
     *
     * @return the headline for the UI.
     */
    @Override
    public String headline() {
        return "Notify Result of Ranking";
    }

    /**
     * Builds the subject for the email to be sent to the candidate.
     *
     * @param application the application to be notified.
     * @return the subject for the email to be sent to the candidate.
     */
    private String buildSubject(Application application) {
        return "Verification of Candidates - " + application.dataFile();
    }

    /**
     * Builds the body for the email to be sent to the candidate.
     *
     * @param application the application to be notified.
     * @param rank the rank of the application.
     * @param status the status of the application.
     * @return the body for the email to be sent to the candidate.
     */
    private String buildBody1(Application application, Rank rank, Status status) {
        return "Dear Candidate,\n\n" +
                "We hope this message finds you well.\n\n" +
                "We are writing this to congratulate you because your application " + application.dataFile() + ", placed in rank " + rank + " has been changed to " + status + ".\n" +
                "You will be contacted soon by our company.\n\n" +
                "Best regards,\n" +
                "Jobs4U\n";
    }

    /**
     * Builds the body for the email to be sent to the customer.
     *
     * @param candidate the candidate to be notified.
     * @return the body for the email to be sent to the customer.
     */
    private String buildBody2(Candidate candidate) {
        return "Candidate: " + candidate.user().email() + ", Telephone number: " + candidate.identity();
    }

    /**
     * Builds the subject for the email to be sent to the customer.
     *
     * @param jobReference the job reference of the job opening.
     * @return the subject for the email to be sent to the customer.
     */
    private String buildSubject1(JobReference jobReference) {
        return "Verification Process - " + jobReference.toString();
    }

}