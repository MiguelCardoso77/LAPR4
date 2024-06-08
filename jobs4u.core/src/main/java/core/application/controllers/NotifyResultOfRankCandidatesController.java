package core.application.controllers;

import core.domain.application.Application;
import core.domain.email.Email;
import core.domain.jobOpening.JobOpening;

import java.util.List;

public class NotifyResultOfRankCandidatesController {

    private final NotifyCandidatesController notifyCandidatesController = new NotifyCandidatesController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();


    public JobOpening selectJobOpening(){
        return selectJobOpeningController.selectJobOpening();
    }

    public Iterable<Application> allApplicationsOfJobOpeningAccepted(JobOpening jobOpening){
        return listJobOpeningApplicationsController.allApplicationsOfJobOpeningAccepted(jobOpening);
    }

    public String findCandidateEmail(Application application){
        return  notifyCandidatesController.findCandidateEmail(application);
    }
    public Email createEmail(String candidateEmail, String subject, String body){
        return notifyCandidatesController.createEmail(candidateEmail , subject,body);
    }

    public void sendEmails(List<Email> emailsToSend) {
        notifyCandidatesController.sendEmails(emailsToSend);
    }

    public void sendEmailCostumer(Email emailsToSend) {
        notifyCandidatesController.sendEmailCostumer(emailsToSend);
    }


}
