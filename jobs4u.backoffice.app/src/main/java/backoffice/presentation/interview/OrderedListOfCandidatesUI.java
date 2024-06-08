package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;

import java.util.List;

public class OrderedListOfCandidatesUI {

    private final OrderedListOfCandidatesController orderedListOfCandidatesController = new OrderedListOfCandidatesController();


    public boolean doShow() {

        JobOpening jobOpening = orderedListOfCandidatesController.selectJobOpening();

        Iterable<Application> applicationList = orderedListOfCandidatesController.allApplicationsOfJobOpening(jobOpening.jobReference());

        List<JobInterview> orderedList = orderedListOfCandidatesController.orderedList(applicationList);

        List<Application> finalList = orderedListOfCandidatesController.applicationList(orderedList);

        displayList(finalList, orderedList);


        return true;
    }

    public void  displayList(List<Application> finalList, List<JobInterview> orderedList) {

    int count= 0;

        if(orderedList != null && finalList!= null)  {
            for (JobInterview jobInterview : orderedList) {
                if (finalList.contains(jobInterview.application())) {
                    count++;
                    if(count == 1) {
                        System.out.printf("%-50s | %-50s | %-30s | \n", "Telephone Number", "Curriculum", "Grade");
                    }
                    Candidate candidate = jobInterview.application().candidate();
                    System.out.printf("%-50s | %-50s | %-30s | \n", candidate.identity(), candidate.curriculum(), jobInterview.score());
                }
            }
        }
        if(count == 0 ){
            System.out.println("There are no candidates with grade for this job opening.");
        }
    }
}





