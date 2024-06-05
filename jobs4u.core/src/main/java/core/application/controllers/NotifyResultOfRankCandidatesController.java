package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;

import java.util.ArrayList;
import java.util.List;

public class NotifyResultOfRankCandidatesController {


    public List<Application> verify( Iterable<Application> allApplicationsOfJobOpening){

        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : allApplicationsOfJobOpening) {
            if (application.status() == Status.ACCEPTED || application.status() == Status.DECLINED) {
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




}