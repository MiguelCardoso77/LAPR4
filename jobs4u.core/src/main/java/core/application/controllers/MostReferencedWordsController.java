package core.application.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import core.domain.application.Application;
import core.services.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MostReferencedWordsController {
    private final ApplicationService applicationService = new ApplicationService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final Logger LOGGER = LoggerFactory.getLogger(MostReferencedWordsController.class);

    public Map<String, Integer> findMostReferencedWords(List<File> candidateFiles) {
        Map<String, Integer> totalWordCount = new HashMap<>();

        for (File file : candidateFiles) {

            Thread thread = new Thread(() -> {
                List<String> fileLines = readFile(file);
                Map<String, Integer> wordCount = countWordsFile(fileLines);

                for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                    totalWordCount.merge(entry.getKey(), entry.getValue(), Integer::sum);
                }
            });

            thread.start();
        }

        return sortMap(totalWordCount);
    }

    private Map<String, Integer> countWordsFile(List<String> fileLines) {
        Map<String, Integer> wordsCounter = new HashMap<>();

        for (String line : fileLines) {
            String[] words = line.split("\\s+");

            for (String word : words) {
                if (!word.isEmpty() && isWord(word)) {
                    word = word.toLowerCase();
                    wordsCounter.put(word, wordsCounter.getOrDefault(word, 0) + 1);
                }
            }
        }

        return wordsCounter;
    }

    private boolean isWord(String possibleWord) {
        return possibleWord.matches("^[a-zA-Z]+$");
    }

    private List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Error reading file: {}", file.getName(), e);
            return Collections.emptyList();
        }
    }

    private Map<String, Integer> sortMap(Map<String, Integer> totalWordCount) {
        return totalWordCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(20)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    public List<Application> findCustomerManagerApplications() {
        SystemUser customerManager = authz.session().map(UserSession::authenticatedUser).orElse(null);

        return applicationService.applicationsByCM(customerManager);
    }

    public List<File> getCandidateFiles(String curriculum) {
        try {

            Path curriculumPath = Paths.get(curriculum);
            return Files.walk(curriculumPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println("An error occurred while accessing the directory: " + e.getMessage());
            return List.of();
        }
    }
}
