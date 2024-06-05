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

    public Map<String, Map<String, Integer>> findMostReferencedWords(List<File> candidateFiles) {
        Map<String, Map<String, Integer>> totalWordCount = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        for (File file : candidateFiles) {
            Thread thread = new Thread(() -> {
                List<String> fileLines = readFile(file);
                Map<String, Map<String, Integer>> wordCount = countWordsFile(file, fileLines);

                synchronized (totalWordCount) {
                    for (Map.Entry<String, Map<String, Integer>> entry : wordCount.entrySet()) {
                        String word = entry.getKey();
                        Map<String, Integer> fileCounts = entry.getValue();

                        totalWordCount.merge(word, fileCounts, (oldMap, newMap) -> {
                            newMap.forEach((fileName, count) -> oldMap.merge(fileName, count, Integer::sum));
                            return oldMap;
                        });
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error("Thread interrupted while waiting for completion", e);
            }
        }

        return sortMap(totalWordCount);
    }

    private Map<String, Map<String, Integer>> countWordsFile(File file, List<String> fileLines) {
        Map<String, Map<String, Integer>> wordsCounter = new HashMap<>();

        for (String line : fileLines) {
            String[] words = line.split("\\s+");

            for (String word : words) {
                if (!word.isEmpty() && isWord(word)) {
                    word = word.toLowerCase();
                    wordsCounter.computeIfAbsent(word, k -> new HashMap<>())
                            .merge(file.getName(), 1, Integer::sum);
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

    private Map<String, Map<String, Integer>> sortMap(Map<String, Map<String, Integer>> totalWordCount) {
        return totalWordCount.entrySet().stream()
                .sorted((e1, e2) -> {
                    int sum1 = e1.getValue().values().stream().mapToInt(Integer::intValue).sum();
                    int sum2 = e2.getValue().values().stream().mapToInt(Integer::intValue).sum();
                    return Integer.compare(sum2, sum1);
                })
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
