package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UploadRequirementsAnswersController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    public List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public Application uploadResponses(List<String> responses, Application application) {
        CandidateRequirements candidateRequirements = new CandidateRequirements(responses);

        application.uploadCandidateRequirements(candidateRequirements);
        System.out.println("\nCandidate Requirements uploaded successfully!");
        return applicationRepository.save(application);
    }
}
