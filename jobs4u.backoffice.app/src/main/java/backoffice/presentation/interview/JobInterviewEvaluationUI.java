package backoffice.presentation.interview;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.InterviewsEvaluationProcessController;
import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.process.ProcessState;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User Interface for evaluating job interview answers.
 *
 * @author 1220812@isep.ipp.pt
 */

public class JobInterviewEvaluationUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    final ListJobOpeningApplicationsController applicationsController = new ListJobOpeningApplicationsController();
    final InterviewsEvaluationProcessController evaluationProcessController = new InterviewsEvaluationProcessController();
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");
        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();





        return false;
    }
    /**
     * Displays a list of job openings that are in the analysis state.
     */
    private void showJobOpenings(){
        final Iterable<JobOpening> iterable = jobOpeningController.allJobOpenings();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job openings");
        }else{
            int cont = 1;
            System.out.println("List of registered Job Openings");
            for (JobOpening jobOpening : iterable) {
                if(jobOpening.process().processState() == ProcessState.ANALYSIS){
                    System.out.printf("%-6s%-30s%n", cont, jobOpening.identity());
                    cont++;
                }
            }
        }
    }
    /**
     * Allows the user to select a job opening from the displayed list.
     *
     * @return the selected JobOpening object
     */
    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningController.allJobOpenings()) {
            if(jobOpening.process().processState() == ProcessState.ANALYSIS){
                list.add(jobOpening);
            }
        }
        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
            System.exit(0);
        } else {
            try {
                jobOpening = this.jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }

    private void executeEvaluationProcess(JobOpening jobOpening){
        Iterable<Application> applications = applicationsController.allApplicationsOfJobOpening(jobOpening.identity());
        for (Application application : applications) {
            //Score newScore = evaluationProcessController.interviewEvaluationProcess(application.interview);
            //evaluationProcessController.interviewScoreUpdate(newScore, application.interview);
        }
    }

    @Override
    public String headline() {
        return "Job interview answers evaluation process";
    }
}
