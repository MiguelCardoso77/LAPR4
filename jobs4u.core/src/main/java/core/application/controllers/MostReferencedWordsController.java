package core.application.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class MostReferencedWordsController {
    private static final int NUM_THREADS = 4;

    // Method to count words in a single file
    private Map<String, Integer> countWordsInFile(File file) throws IOException {
        Map<String, Integer> wordCounts = new HashMap<>();
        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        return wordCounts;
    }

    // Method to count words in multiple files using parallelism
    private Map<String, Integer> countWordsInFiles(List<File> files) throws InterruptedException {
        Map<String, Integer> aggregatedCounts = Collections.synchronizedMap(new HashMap<>());
        List<Thread> threads = new ArrayList<>();
        int currentIndex = 0;

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(() -> {
                int index;
                while ((index = getIncrement(currentIndex)) < files.size()) {
                    File file = files.get(index);
                    try {
                        Map<String, Integer> fileCounts = countWordsInFile(file);
                        synchronized (aggregatedCounts) {
                            for (Map.Entry<String, Integer> entry : fileCounts.entrySet()) {
                                aggregatedCounts.put(entry.getKey(), aggregatedCounts.getOrDefault(entry.getKey(), 0) + entry.getValue());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return aggregatedCounts;
    }

    // Method to get top N words
    private List<Map.Entry<String, Integer>> getTopWords(Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(20)
                .collect(Collectors.toList());
    }

    // Method to track which files contain the top words
    private Map<String, Set<File>> trackWordsInFiles(List<File> files) throws InterruptedException {
        Map<String, Set<File>> wordFileMap = Collections.synchronizedMap(new HashMap<>());
        List<Thread> threads = new ArrayList<>();
        int currentIndex = 0;

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(() -> {
                int index;
                while ((index = getIncrement(currentIndex)) < files.size()) {
                    File file = files.get(index);
                    try {
                        Map<String, Integer> fileCounts = countWordsInFile(file);
                        synchronized (wordFileMap) {
                            for (String word : fileCounts.keySet()) {
                                wordFileMap.computeIfAbsent(word, k -> Collections.synchronizedSet(new HashSet<>())).add(file);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return wordFileMap;
    }

    public List<Map.Entry<String, Set<File>>> getMostReferencedWords(List<File> candidateFiles) throws InterruptedException {
        // Count words in the files
        Map<String, Integer> wordCounts = countWordsInFiles(candidateFiles);

        // Get the top 20 words
        List<Map.Entry<String, Integer>> topWords = getTopWords(wordCounts);

        // Track the files for each word
        Map<String, Set<File>> wordFileMap = trackWordsInFiles(candidateFiles);

        // Combine the top words with their file references
        List<Map.Entry<String, Set<File>>> topWordsWithFiles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : topWords) {
            topWordsWithFiles.add(new AbstractMap.SimpleEntry<>(entry.getKey(), wordFileMap.get(entry.getKey())));
        }

        return topWordsWithFiles;
    }

    public int getIncrement(int num) {
        return num++;
    }
}
