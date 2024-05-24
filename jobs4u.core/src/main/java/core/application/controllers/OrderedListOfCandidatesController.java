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

    public List<JobInterview> orderedList(Iterable<Application> applicationList){
        List<JobInterview> orderedList = new ArrayList<>();

        for(Application application : applicationList){
            Iterable<JobInterview> list = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
            for(JobInterview jobInterview : list){
                 orderedList.add(jobInterview);
            }
        }

        orderedList.sort(Comparator.comparing(JobInterview::returnScore).reversed());

        return orderedList;
    }


    public List<Application> applicationList (List<JobInterview> orderedList){
        List<Application> applicationsList = new ArrayList<>();


        for(JobInterview jobInterview : orderedList){
            Application application = jobInterview.application();
            applicationsList.add(application);}




        return applicationsList;
    }

}
