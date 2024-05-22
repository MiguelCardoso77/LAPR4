package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
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
        JobReference jobReference =  jobOpening.jobReference();

        Iterable<Application> applicationList = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);

        List<JobInterview> desorderedList = orderedListOfCandidatesController.desorderedList(applicationList);

        List<JobInterview> orderedListfinal = sortJobInterviewsByGrade(desorderedList);



        return true;
    }



    /*private JobOpening selectJobOpening() {
        List<JobOpening> jobOpenings = new ArrayList<>();
        Iterable<JobOpening> allJobOpenings = listJobOpeningController.allJobOpenings();

        if (!allJobOpenings.iterator().hasNext()) {
            System.out.println("There are no job openings available.");
            return null;
        } else {
            System.out.printf("%-30s%-30s%n", "Job Opening ID", "Title");
            int index = 1;
            for (JobOpening jobOpening : allJobOpenings) {
                jobOpenings.add(jobOpening);
                System.out.printf("%-30s%-30s%n", index, jobOpening.jobReference());
                index++;
            }
            final int option = Console.readInteger("Enter the number of the job opening to select (0 to cancel):");
            if (option == 0) {
                System.out.println("No job opening selected.");
                return null;
            } else if (option > 0 && option <= jobOpenings.size()) {
                return jobOpenings.get(option - 1);
            } else {
                System.out.println("Invalid option selected.");
                return null;
            }
        }*/


    /*public List<JobInterview> orderedList(Iterable<Application> applicationList){
        List<JobInterview> desorderedList = null;
        List<JobInterview> orderedListfinal = null;

        for(Application application : applicationList){
            Iterable<JobInterview> list = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);

            for(JobInterview jobInterview : list){
                desorderedList.add(jobInterview);
            }
        }


        return orderedListfinal;
    }*/


    /*public List<JobInterview> orderedListOfCandidates(List<JobInterview> orderedList) {
        List<JobInterview> orderedListfinal = null;

        for (JobInterview jobInterview : orderedList) {


        }
        return orderedListfinal;


    }*/

    public static List<JobInterview> sortJobInterviewsByGrade(List<JobInterview> desorderedList) {
        if (desorderedList == null || desorderedList.isEmpty()) {
            System.out.println("There are no job interviews to show.");
            return desorderedList;
        }

        System.out.println("amo-te<3");

        Collections.sort(desorderedList, Comparator.comparing(
                JobInterview::returnScore
        ).reversed());

        System.out.println("mais");


        System.out.printf("%-30s | %-30s | %-30s | %-30s | \n", "JobInterview ID", "Application_ID", "Score", "Result");
        for (JobInterview jobInterview : desorderedList) {
            Score score = jobInterview.score();
            String scoreString = (score != null) ? String.valueOf(jobInterview.score()) : "N/A";
            System.out.printf("%-30s | %-30s | %-30s | %-30s | \n", jobInterview.identity(), jobInterview.application(), scoreString, jobInterview.result());
        }

        return desorderedList;
    }


}



