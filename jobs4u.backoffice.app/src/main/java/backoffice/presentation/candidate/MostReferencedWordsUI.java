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
                System.out.println("Candidate: " + application.candidate().toString());
                System.out.println("Job Opening: " + application.jobReference().toString());
                System.out.println("Status: " + application.status().toString());
                System.out.println("Rank: " + application.rank().toString());
                System.out.println("Submission Date: " + application.submissionDate().toString());
                System.out.println("Files: " + application.dataFile());
                System.out.println();
            }

            Candidate selectedCandidate = selectCandidate(applicationsFromCM);
            List<File> candidateFiles = theController.getCandidateFiles(selectedCandidate.curriculum().curriculumPath());

            Map<String, Integer> topWords = theController.findMostReferencedWords(candidateFiles);

            System.out.println(ConsoleColors.CYAN + "\nTop 20 most referenced words in the files provided by the Candidate: " + ConsoleColors.RESET);
            for (Map.Entry<String, Integer> entry : topWords.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + " occurrences");
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
