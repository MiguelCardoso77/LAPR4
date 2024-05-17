package core.application.controllers;

import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateInterviewModelController {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public void writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file.");
        }
    }

    public List<JobInterview> findAllInterviews() {
        return (List<JobInterview>) jobInterviewRepository.allJobInterviews();
    }

    public InterviewModel getInterviewModelByJobInterviewID(int jobInterviewID) {
        Optional<JobInterview> jobInterview = jobInterviewRepository.ofIdentity(jobInterviewID);

        return jobInterview.get().interviewModel();
    }

    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(">")) {
                processedLines.add(line.substring(0, line.indexOf(">") + 1));
            } else {
                processedLines.add(line);
            }
        }
        return processedLines;
    }
}
