package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;
import core.services.JobOpeningService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsSpecificationController {
    private final JobOpeningService jobOpeningService = new JobOpeningService();


    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            System.out.println("\nFile created successfully.");
        } catch (IOException e) {
            System.err.println("\nError writing to file.");
            e.printStackTrace();
        }
    }

    public List<JobOpening> findAllJobOpeningAssigned() {
        List<JobOpening> filteredJobOpening = new ArrayList<>();

        for (JobOpening jobOpening : jobOpeningService.allJobOpenings()) {
            if (jobOpening.jobRequirementsSpecification() != null) {
                filteredJobOpening.add(jobOpening);
            }
        }

        return filteredJobOpening;
    }


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
