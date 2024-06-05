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

public class MostReferencedWordsUI extends AbstractUI {
    private final MostReferencedWordsController theController = new MostReferencedWordsController();

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

    @Override
    public String headline() {
        return "Top 20 Most Referenced Words";
    }
}
