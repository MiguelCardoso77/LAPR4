package backoffice.presentation.candidate;

import core.application.controllers.MostReferencedWordsController;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostReferencedWordsUI extends AbstractUI {
    private final MostReferencedWordsController theController = new MostReferencedWordsController();

    @Override
    protected boolean doShow() {
        try {
            // Assume getCandidateFiles() returns the list of files uploaded by the candidate
            List<File> candidateFiles = getCandidateFiles();

            // Get the top 20 most referenced words and the files they appear in
            List<Map.Entry<String, Set<File>>> topWords = theController.getMostReferencedWords(candidateFiles);

            // Display the top 20 words and their files
            System.out.println(headline());
            for (Map.Entry<String, Set<File>> entry : topWords) {
                System.out.println(entry.getKey() + ": " + entry.getValue().size() + " occurrences");
                System.out.println("Files: " + entry.getValue());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while processing the files: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

    private List<File> getCandidateFiles() {
        // This method should be implemented to fetch the list of files uploaded by the candidate.
        // For now, returning an empty list.
        return List.of();
    }

    @Override
    public String headline() {
        return "Top 20 Most Referenced Words";
    }
}
