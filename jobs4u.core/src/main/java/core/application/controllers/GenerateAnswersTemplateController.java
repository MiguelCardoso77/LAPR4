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

public class GenerateAnswersTemplateController {
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

    public List<JobInterview> findAllInterviewsWithModelAssigned() {
        List<JobInterview> allJobInterviews = (List<JobInterview>) jobInterviewRepository.allJobInterviews();
        List<JobInterview> filteredJobInterviews = new ArrayList<>();

        for (JobInterview jobInterview : allJobInterviews) {
            if (jobInterview.interviewModel() != null) {
                filteredJobInterviews.add(jobInterview);
            }
        }

        return filteredJobInterviews;
    }

    public InterviewModel getInterviewModelByJobInterviewID(int jobInterviewID) {
        Optional<JobInterview> jobInterview = jobInterviewRepository.ofIdentity(jobInterviewID);

        return jobInterview.get().interviewModel();
    }

    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            int firstIndex = line.indexOf(">");
            if (firstIndex != -1) {
                int secondIndex = line.indexOf(">", firstIndex + 1);
                if (secondIndex != -1) {
                    processedLines.add(line.substring(0, secondIndex + 1));
                } else {
                    processedLines.add(line);
                }
            } else {
                processedLines.add(line);
            }
        }
        return processedLines;
    }
}
