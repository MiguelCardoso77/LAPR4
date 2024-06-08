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

public class NotifyResultOfRankCandidatesUI extends AbstractUI {

    private final NotifyResultOfRankCandidatesController notifyResultOfRankCandidatesController = new NotifyResultOfRankCandidatesController();

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

            Email emailcostumer = notifyResultOfRankCandidatesController.createEmail(costumerEmail, subject1, body3);

            notifyResultOfRankCandidatesController.sendEmails(emailsToSend);

            notifyResultOfRankCandidatesController.sendEmailCostumer(emailcostumer);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Notify Result of Ranking";
    }

    private String buildSubject(Application application) {
        return "Verification of Candidates - " + application.dataFile();
    }


    private String buildBody1(Application application, Rank rank, Status status) {
        return "Dear Candidate,\n\n" +
                "We hope this message finds you well.\n\n" +
                "We are writing this to congratulate you because your application " + application.dataFile() + ", placed in rank " + rank + " has been changed to " + status + ".\n" +
                "You will be contacted soon by our company.\n\n" +
                "Best regards,\n" +
                "Jobs4U\n";
    }

    private String buildBody2(Candidate candidate) {
        return "Candidate: " + candidate.user().email() + ", Telephone number: " + candidate.identity();
    }

    private String buildSubject1(JobReference jobReference) {
        return "Verification Process - " + jobReference.toString();
    }

}