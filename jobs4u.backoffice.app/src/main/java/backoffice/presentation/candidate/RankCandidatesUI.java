package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.RankCandidatesController;
import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class RankCandidatesUI extends AbstractUI {
    private final RankCandidatesController theController = new RankCandidatesController();

    @Override
    protected boolean doShow() {
        JobOpening selectedJob = selectJobOpening();
        Application selectedApplication = selectApplication(selectedJob);
        Score selectedInterview = selectJobInterview(selectedApplication);

        System.out.println(ConsoleColors.GREEN + "The score of the selected application is" + selectedInterview + ConsoleColors.RESET);
        int rank = Console.readInteger("Insert the new rank of the candidate: ");

        updateRank(rank, selectedApplication);

        return true;
    }

    private JobOpening selectJobOpening() {
        List<JobOpening> jobOpenings = theController.getAllJobOpenings();

        System.out.println("Job Openings:");
        for (int i = 0; i < jobOpenings.size(); i++) {
            System.out.println((i + 1) + " - " + jobOpenings.get(i).jobReference());
        }

        int selectedNumber = Console.readInteger("Please select a job opening by entering its number: ");
        if (selectedNumber < 1 || selectedNumber > jobOpenings.size()) {
            System.out.println(ConsoleColors.RED + "Invalid number. Please enter a number between 1 and " + jobOpenings.size() + ConsoleColors.RESET);
        }

        return jobOpenings.get(selectedNumber - 1);
    }

    private Application selectApplication(JobOpening selectedJob) {
        List<Application> applications = theController.getApplicationsForJobOpening(selectedJob);

        if (applications.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No applications found for the selected job opening." + ConsoleColors.RESET);
            return null;
        }

        System.out.println("\nApplications:");
        for (int i = 0; i < applications.size(); i++) {
            System.out.println((i + 1) + " - " + applications.get(i).dataFile());
        }

        int selectedApplicationIndex = Console.readInteger("Please select an application by entering its number: ");
        if (selectedApplicationIndex < 1 || selectedApplicationIndex > applications.size()) {
            System.out.println(ConsoleColors.RED + "Invalid number. Please enter a number between 1 and " + applications.size() + ConsoleColors.RESET);
        }

        return applications.get(selectedApplicationIndex - 1);
    }

    private Score selectJobInterview(Application selectedApplication) {
        List<JobInterview> interviews = theController.getInterviewsForApplication(selectedApplication);

        if (interviews.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No interviews found for the selected application." + ConsoleColors.RESET);
            return null;
        }

        System.out.println("\nJob Interviews:");
        for (int i = 0; i < interviews.size(); i++) {
            System.out.println((i + 1) + " - " + interviews.get(i).identity());
        }

        int selectedInterviewIndex = Console.readInteger("Please select a job interview by entering its number: ");
        if (selectedInterviewIndex < 1 || selectedInterviewIndex > interviews.size()) {
            System.out.println(ConsoleColors.RED + "Invalid number. Please enter a number between 1 and " + interviews.size() + ConsoleColors.RESET);
        }

        return interviews.get(selectedInterviewIndex - 1).score();
    }

    private void updateRank(int rank, Application selectedApplication) {
        Application newApplication = theController.updateRank(rank, selectedApplication);
        System.out.println("Rank was updated to " + newApplication.rank() + " for the application " + newApplication.dataFile());
    }

    @Override
    public String headline() {
        return "Rank Candidates";
    }
}
