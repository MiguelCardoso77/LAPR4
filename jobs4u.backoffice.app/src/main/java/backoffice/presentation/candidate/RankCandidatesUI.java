package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.RankCandidatesController;
import core.application.controllers.SelectJobOpeningController;
import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Comparator;
import java.util.List;

public class RankCandidatesUI extends AbstractUI {
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final RankCandidatesController theController = new RankCandidatesController();
    private final boolean RANK_ONLY_NOT_RANKED = false;

    @Override
    protected boolean doShow() {
        JobOpening selectedJob = selectJobOpeningController.selectJobOpeningAnalysis();

        List<Application> applications = theController.findApplicationsForJobOpening(selectedJob);
        if (applications.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No applications found for the selected job opening." + ConsoleColors.RESET);
            return false;
        }

        if (!RANK_ONLY_NOT_RANKED) {

            System.out.println("\n" + applications.size() + " applications will be ranked.");
            for (Application application : applications) {
                rankApplication(application, applications.size());
            }

        } else {

            List<Application> nonRankedApplications = theController.filterByNonRankedApplications(applications);

            if (nonRankedApplications.isEmpty()) {
                System.out.println(ConsoleColors.RED + "No non-ranked applications found for the selected job opening." + ConsoleColors.RESET);
                return false;
            }

            System.out.println("\n" + applications.size() + " applications will be ranked.");
            for (Application application : nonRankedApplications) {
                rankApplication(application, applications.size());
            }

        }

        theController.clearAssignedRanks();
        return true;
    }

    private void rankApplication(Application application, int listSize) {
        System.out.println("\nNow ranking, Application: " + application.dataFile());

        Score selectedInterview = getLastJobInterview(application);
        System.out.println(ConsoleColors.CYAN + "The score of the last interview for this application is " + ConsoleColors.RESET + selectedInterview);

        if (selectedInterview == null) {
            String response = Console.readLine("Do you still want to rank the application? (Yes/No): ");
            if (!response.equalsIgnoreCase("yes")) {
                return;
            }
        }

        int rank;
        do {
            rank = Console.readInteger("Insert the new rank for this candidate: ");
            if (rank < 1 || rank > listSize) {
                System.out.println(ConsoleColors.RED + "Invalid rank. Please enter a rank between 1 and " + listSize + "." + ConsoleColors.RESET);
            }
            if (theController.isRankAlreadyAssigned(rank)) {
                System.out.println(ConsoleColors.RED + "This rank is already assigned. Please choose a different rank." + ConsoleColors.RESET);
            }
        } while (rank < 1 || rank > listSize || theController.isRankAlreadyAssigned(rank));

        updateRank(rank, application);
    }

    private Score getLastJobInterview(Application selectedApplication) {
        List<JobInterview> interviews = theController.findInterviewsForApplication(selectedApplication);

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
        System.out.println(ConsoleColors.GREEN + "Success:" + ConsoleColors.RESET + " Rank was updated to " + newApplication.rank() + " for the application " + newApplication.dataFile());
    }

    @Override
    public String headline() {
        return "Rank Candidates, (Ranking Only Not Ranked: " + RANK_ONLY_NOT_RANKED + ")";
    }
}
