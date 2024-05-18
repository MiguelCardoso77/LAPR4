package core.application.controllers;

import core.domain.interview.InterviewAnswers;
import core.domain.interview.JobInterview;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import eapli.framework.application.UseCaseController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@UseCaseController
public class UploadResponsesController {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public JobInterview findInterviewByID(int jobInterviewID) {
        return jobInterviewRepository.ofIdentity(jobInterviewID).orElse(null);
    }

    public JobInterview uploadResponses(List<String> responses, JobInterview jobInterview) {
        InterviewAnswers interviewAnswers = new InterviewAnswers(responses);

        jobInterview.uploadInterviewAnswers(interviewAnswers);
        System.out.println("\nResponses uploaded successfully!");
        return jobInterviewRepository.save(jobInterview);
    }
}
