package backoffice.presentation.candidate;

import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.application.controllers.NotifyCandidatesController;
import core.application.controllers.NotifyResultOfRankCandidatesController;
import core.domain.application.Application;
import core.domain.application.Rank;
import core.domain.application.Status;
import core.domain.email.Email;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotifyResultOfRankCandidatesUI {

    private final NotifyCandidatesController notifyCandidatesController = new NotifyCandidatesController();
    private final NotifyResultOfRankCandidatesController notifyResultOfRankCandidatesController = new NotifyResultOfRankCandidatesController();
    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();


    protected boolean doShow() {

        List<Email> emailsToSend = new ArrayList<>();


        listJobOpeningController.showJobOpenings();

        int selectedJobOpening = selectJobOpening();

        JobOpening jobOpening = listJobOpeningController.getJobOpeningAt(listJobOpeningController.allJobOpenings(), selectedJobOpening);

        JobReference jobReference = jobOpening.jobReference();

        Iterable<Application> allApplicationsOfJobOpening = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);

        List<Application> appToNotify = notifyResultOfRankCandidatesController.verify(allApplicationsOfJobOpening);

        for (Application application : appToNotify) {
            boolean ranked = notifyResultOfRankCandidatesController.accepted(application, jobOpening);

            if (ranked) {
                String candidateEmail = notifyCandidatesController.findCandidateEmail(application);
                String subject = buildSubject(application);
                String body = buildBody1(application, application.rank(), application.status());
                Email emailObj = notifyCandidatesController.createEmail(candidateEmail, subject, body);
                emailsToSend.add(emailObj);
                System.out.println(emailObj);


            } else {
                String candidateEmail = notifyCandidatesController.findCandidateEmail(application);
                String subject = buildSubject(application);
                String body = buildBody1(application, application.rank(), application.status());
                Email emailObj = notifyCandidatesController.createEmail(candidateEmail, subject, body);
                emailsToSend.add(emailObj);
                System.out.println(emailObj);


            }

            notifyCandidatesController.sendEmails(emailsToSend);

        }


        return true;
    }


    public int selectJobOpening() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the index of the job opening you want to select: ");
        int index = scanner.nextInt();
        scanner.close();
        return index;
    }

    private String buildSubject(Application application) {
        return "Verification Process - " + application.dataFile();
    }


    private String buildBody1(Application application, Rank rank, Status status) {
        return "Through your application"+ application.identity()  + ", you placed in rank " +  rank + "so you have been " + status+ "." ;
    }
}