package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Controller responsible for generating ordered lists of job interviews and applications
 * based on interview scores for a specific job opening.
 *
 * @author Tomás Gonçalves
 */
public class OrderedListOfCandidatesController {
    private final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    /**
     * Generates an ordered list of job interviews for the given list of applications,
     * sorted by the interview scores in descending order.
     *
     * @param applicationList An iterable of applications for a job opening.
     * @return A list of job interviews ordered by their scores in descending order.
     */
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

    /**
     * Generates a list of applications based on the ordered list of job interviews.
     *
     * @param orderedList A list of job interviews ordered by their scores.
     * @return A list of applications corresponding to the ordered job interviews.
     */
    public List<Application> applicationList (List<JobInterview> orderedList){
        List<Application> applicationsList = new ArrayList<>();

        for(JobInterview jobInterview : orderedList){
            Application application = jobInterview.application();
            applicationsList.add(application);}

        return applicationsList;
    }
    /**
     * Allows the user to select a job opening.
     *
     * @return The selected JobOpening object.
     */
    public JobOpening selectJobOpening(){
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Retrieves all applications associated with a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return An iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference){
        return listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);
    }
}