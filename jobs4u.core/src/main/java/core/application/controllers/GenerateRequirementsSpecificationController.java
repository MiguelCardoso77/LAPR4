package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.services.JobOpeningService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for generating requirements specifications.
 * This class provides methods for reading and writing files, filtering job openings with specifications,
 * and processing lines of text.
 *
 * @author Diana Neves
 */
public class GenerateRequirementsSpecificationController {
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    /**
     * Reads all lines from a file.
     *
     * @param filePath The path to the file to be read
     * @return A list of strings containing the lines read from the file
     */
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Writes a list of strings to a file.
     *
     * @param questionForFile    The list of strings to be written to the file
     * @param filePath The path to the file to write to
     */
    public void writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            System.out.println("\nFile created successfully.");
        } catch (IOException e) {
            System.err.println("\nError writing to file.");
            e.printStackTrace();
        }
    }

    /**
     * Finds all job openings with assigned job requirements specifications.
     *
     * @return A list of JobOpening objects representing the job openings with specifications
     */
    public List<JobOpening> findAllJobOpeningAssigned() {
        List<JobOpening> filteredJobOpening = new ArrayList<>();

        for (JobOpening jobOpening : jobOpeningService.allJobOpenings()) {
            if (jobOpening.jobRequirementsSpecification() != null) {
                filteredJobOpening.add(jobOpening);
            }
        }

        return filteredJobOpening;
    }

    /**
     * Processes lines of text.
     * This method adds lines containing ":" as is, and removes everything after ":" otherwise.
     *
     * @param lines The list of strings representing lines of text to be processed
     * @return The processed list of strings
     */
    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            int firstIndex = line.indexOf(":");
            if (firstIndex != -1) {
                // Keep everything up to and including the first ":"
                processedLines.add(line.substring(0, firstIndex + 1));
            } else {
                // Add the line as is if no ":" is found
                processedLines.add(line);
            }
        }
        return processedLines;
    }
}
