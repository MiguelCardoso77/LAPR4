package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderedListOfCandidatesUI {

    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    private final ListApplicationsController listApplicationsController = new ListApplicationsController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final VerificationRequirementsController verificationRequirementsController = new VerificationRequirementsController();
    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final OrderedListOfCandidatesController orderedListOfCandidatesController = new OrderedListOfCandidatesController();


    public boolean doShow() {

        JobOpening jobOpening = selectJobOpeningController.selectJobOpening();
        JobReference jobReference = jobOpening.jobReference();

        Iterable<Application> applicationList = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);

        List<JobInterview> desorderedList = orderedListOfCandidatesController.desorderedList(applicationList);

        List<Application> finalList = orderedListOfCandidatesController.applicationList(desorderedList);

        displayList(finalList, desorderedList);


        return true;
    }

    public List<Candidate> displayList(List<Application> finalList, List<JobInterview> desorderedList) {

    int count= 0;

        if(desorderedList != null && finalList!= null)  {
            for (JobInterview jobInterview : desorderedList) {
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

            return null;
        }
    }





