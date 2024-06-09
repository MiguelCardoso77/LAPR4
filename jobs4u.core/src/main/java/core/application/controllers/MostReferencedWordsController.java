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
/**
 * Controller for finding the most referenced words in the Jobs4U system.
 * This class provides methods to find the most referenced words, count words in a file,
 * check if a string is a word, read a file, sort a map, find applications by a customer manager,
 * and get candidate files.
 * It uses the ApplicationService and AuthorizationService from the eapli framework.
 *
 * @author Miguel Cardoso
 * 
 */
public class MostReferencedWordsController {
    private final ApplicationService applicationService = new ApplicationService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final Logger LOGGER = LoggerFactory.getLogger(MostReferencedWordsController.class);
    /**
     * Finds the most referenced words in a list of files.
     *
     * @param candidateFiles the list of files to find the most referenced words in
     * @return a map of the most referenced words and their counts
     */
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
    /**
     * Counts the words in a file.
     *
     * @param file the file to count the words in
     * @param fileLines the lines of the file
     * @return a map of the words and their counts
     */
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
    /**
     * Checks if a string is a word.
     *
     * @param possibleWord the string to check
     * @return true if the string is a word, false otherwise
     */
    private boolean isWord(String possibleWord) {
        return possibleWord.matches("^[a-zA-Z]+$");
    }
    /**
     * Reads a file and returns its lines.
     *
     * @param file the file to read
     * @return a list of the lines in the file
     */
    private List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Error reading file: {}", file.getName(), e);
            return Collections.emptyList();
        }
    }
    /**
     * Sorts a map by its values in descending order and limits the map to the top 20 entries.
     *
     * @param totalWordCount the map to sort
     * @return the sorted map
     */
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
    /**
     * Finds the applications by a customer manager.
     *
     * @return a list of the applications by the customer manager
     */
    public List<Application> findCustomerManagerApplications() {
        SystemUser customerManager = authz.session().map(UserSession::authenticatedUser).orElse(null);

        return applicationService.applicationsByCM(customerManager);
    }
    /**
     * Gets the candidate files from a curriculum.
     *
     * @param curriculum the curriculum to get the candidate files from
     * @return a list of the candidate files
     */
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
