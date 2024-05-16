package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.RankCandidatesController;
import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Comparator;
import java.util.List;

public class RankCandidatesUI extends AbstractUI {
    private final RankCandidatesController theController = new RankCandidatesController();
    private final boolean RANK_ONLY_NOT_RANKED = false;

    @Override
    protected boolean doShow() {
        JobOpening selectedJob = selectJobOpening();

        List<Application> applications = allApplicationsForJobOpening(selectedJob);
        if (applications.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No applications found for the selected job opening." + ConsoleColors.RESET);
            return false;
        }

        if (!RANK_ONLY_NOT_RANKED) {

            for (Application application : applications) {
                rankApplication(application);
            }

        } else {

            List<Application> nonRankedApplications = theController.filterByNonRankedApplications(applications);

            if (nonRankedApplications.isEmpty()) {
                System.out.println(ConsoleColors.RED + "No non-ranked applications found for the selected job opening." + ConsoleColors.RESET);
                return false;
            }

            for (Application application : nonRankedApplications) {
                rankApplication(application);
            }

        }

        return true;
    }

    private void rankApplication(Application application) {
        System.out.println("\nNow ranking, Application: " + application.dataFile());

        Score selectedInterview = getLastJobInterview(application);
        System.out.println(ConsoleColors.GREEN + "The score of the last interview for this application is " + ConsoleColors.RESET + selectedInterview);

        if (selectedInterview == null) {
            String response = Console.readLine("Do you still want to rank the application? (Yes/No): ");
            if (!response.equalsIgnoreCase("yes")) {
                return;
            }
        }

        int rank = Console.readInteger("Insert the new rank for this candidate: ");

        updateRank(rank, application);
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

    private List<Application> allApplicationsForJobOpening(JobOpening selectedJob) {
        return theController.getApplicationsForJobOpening(selectedJob);
    }

    private Score getLastJobInterview(Application selectedApplication) {
        List<JobInterview> interviews = theController.getInterviewsForApplication(selectedApplication);

        if (interviews.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No interviews found for the selected application." + ConsoleColors.RESET);
            return null;
        }

        interviews.sort(Comparator.comparing(JobInterview::createdOn));

        JobInterview mostRecentInterview = interviews.get(interviews.size() - 1);

        return mostRecentInterview.score();
    }

    private void updateRank(int rank, Application selectedApplication) {
        Application newApplication = theController.updateRank(rank, selectedApplication);
        System.out.println("Success: Rank was updated to " + newApplication.rank() + " for the application " + newApplication.dataFile());
    }

    @Override
    public String headline() {
        return "Rank Candidates, (Ranking Only Not Ranked: " + RANK_ONLY_NOT_RANKED + ")";
    }
}
