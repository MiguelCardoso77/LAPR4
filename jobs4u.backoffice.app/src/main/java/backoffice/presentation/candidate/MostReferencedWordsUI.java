package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.MostReferencedWordsController;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * UI class responsible for displaying the top 20 most referenced words in candidate curricula.
 * It interacts with the user to select a candidate and retrieves the files associated with the candidate's curriculum.
 * It then processes these files to find the most referenced words and displays the results.
 * This class utilizes the {@link MostReferencedWordsController} to perform the necessary operations.
 * Upon execution, it returns true to indicate the successful completion of the operation.
 *
 * @author Miguel Cardoso
 */
public class MostReferencedWordsUI extends AbstractUI {
    private final MostReferencedWordsController theController = new MostReferencedWordsController();

    /**
     * Displays the top 20 most referenced words in candidate curricula.
     * It first retrieves applications from the customer manager, then prompts the user to select a candidate.
     * After selecting the candidate, it retrieves the candidate's files and finds the most referenced words in them.
     * The results are displayed to the user.
     *
     * @return true indicating the successful execution of the operation.
     */
    @Override
    protected boolean doShow() {
        try {
            List<Application> applicationsFromCM = theController.findCustomerManagerApplications();

            for (Application application : applicationsFromCM) {
                System.out.println("Application ID: " + application.identity());
                System.out.println("Candidate: " + application.candidate().user().email());
                System.out.println("Job Opening: " + application.jobReference().jobReference());
                System.out.println("Files: " + application.dataFile());
                System.out.println();
            }

            Candidate selectedCandidate = selectCandidate(applicationsFromCM);
            assert selectedCandidate != null;
            List<File> candidateFiles = theController.getCandidateFiles(selectedCandidate.curriculum().curriculumPath());

            Map<String, Map<String, Integer>> topWords = theController.findMostReferencedWords(candidateFiles);

            int countPlacement = 1;
            System.out.println(ConsoleColors.CYAN + "\nTop 20 most referenced words in the files provided by the Candidate: " + ConsoleColors.RESET);
            for (Map.Entry<String, Map<String, Integer>> entry : topWords.entrySet()) {
                String currentWord = entry.getKey();
                Map<String, Integer> fileCounts = entry.getValue();

                int totalOccurrences = 0;
                for (int count : fileCounts.values()) {
                    totalOccurrences += count;
                }

                System.out.println("\n" + ConsoleColors.PURPLE + countPlacement + ". " + ConsoleColors.RESET + currentWord + " : " + totalOccurrences + " occurrences");

                for (Map.Entry<String, Integer> fileEntry : fileCounts.entrySet()) {
                    String fileName = fileEntry.getKey();
                    int count = fileEntry.getValue();
                    System.out.println("  " + fileName + " : " + count + " occurrences");
                }

                countPlacement++;
            }

        } catch (Exception e) {
            System.err.println("An error occurred while processing the files: " + e.getMessage());
        }

        return true;
    }

    /**
     * Helper method to select a candidate from the provided list of applications.
     *
     * @param applications the list of applications from which to select a candidate.
     * @return the selected candidate.
     */
    private Candidate selectCandidate(List<Application> applications) {
        int applicationId = Console.readInteger("Enter the ID of the application to select a candidate: ");

        for (Application application : applications) {
            if (application.identity() == applicationId) {
                return application.candidate();
            }
        }

        System.out.println("No application found with the provided ID.");
        return null;
    }

    /**
     * Provides the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Top 20 Most Referenced Words";
    }
}
