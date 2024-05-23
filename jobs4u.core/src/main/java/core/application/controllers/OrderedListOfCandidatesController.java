package core.application.controllers;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderedListOfCandidatesController {


    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    private final ListApplicationsController listApplicationsController = new ListApplicationsController();

    public List<JobInterview> desorderedList(Iterable<Application> applicationList){
        List<JobInterview> desorderedList = new ArrayList<>();

        for(Application application : applicationList){
            Iterable<JobInterview> list = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
            for(JobInterview jobInterview : list){
                 desorderedList.add(jobInterview);
            }
        }

        desorderedList.sort(Comparator.comparing(JobInterview::returnScore).reversed());

        return desorderedList;
    }


    public List<Candidate> candidateList (List<JobInterview> orderedListfinal){
        List<Candidate> candidatesList = new ArrayList<>();
        Iterable<Application> allApplications = listApplicationsController.allApplications();


        for(JobInterview jobInterview : orderedListfinal){
            Application application = jobInterview.application();
            Candidate candidate = application.candidate();
            candidatesList.add(candidate);}




        return candidatesList;
    }

    public List<Application> applicationList (List<JobInterview> orderedListfinal){
        List<Application> applicationsList = new ArrayList<>();
        Iterable<Application> allApplications = listApplicationsController.allApplications();


        for(JobInterview jobInterview : orderedListfinal){
            Application application = jobInterview.application();
            //Candidate candidate = application.candidate();
            applicationsList.add(application);}




        return applicationsList;
    }

}
