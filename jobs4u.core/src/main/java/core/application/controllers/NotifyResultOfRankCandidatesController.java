package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.email.Email;
import core.domain.jobOpening.JobOpening;
import core.services.EmailService;

import java.util.ArrayList;
import java.util.List;

public class NotifyResultOfRankCandidatesController {

    private final EmailService emailService = new EmailService();


    public List<Application> verify( Iterable<Application> allApplicationsOfJobOpening){

        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : allApplicationsOfJobOpening) {
            if (application.status() == Status.ACCEPTED) {
                appsToNotify.add(application);
            }
        }

        return appsToNotify;
    }




    public Boolean accepted(Application application, JobOpening jobOpening){

        int rank = Integer.valueOf(application.rank().toString());
        int vagas = Integer.valueOf(jobOpening.vacanciesNumber().toString());
        if(rank <= vagas && application.status().equals("ACCEPTED")){
            return true;
        }else{
            return false;
        }
    }


    public Email createEmail2(String candidateEmail, String subject, String body) {
        return emailService.createEmail(candidateEmail, subject, body);
    }




}