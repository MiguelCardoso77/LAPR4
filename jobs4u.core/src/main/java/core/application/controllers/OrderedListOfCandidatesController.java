package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;

import java.util.ArrayList;
import java.util.List;

public class OrderedListOfCandidatesController {


    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();


    public List<JobInterview> desorderedList(Iterable<Application> applicationList){
        List<JobInterview> desorderedList = new ArrayList<>();

        for(Application application : applicationList){
            Iterable<JobInterview> list = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);

            for(JobInterview jobInterview : list){
                 desorderedList.add(jobInterview);
            }
        }
        return desorderedList;
    }
}
