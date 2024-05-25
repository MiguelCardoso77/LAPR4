package core.application.controllers;

import core.domain.application.Application;
import core.domain.interviewModel.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import core.repositories.JobOpeningRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateAnswersTemplateController {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public boolean writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<JobInterview> findAllInterviewsWithModelAssigned(List<Application> applications) {
        List<JobInterview> jobInterviews = (List<JobInterview>) jobInterviewRepository.allJobInterviews();
        List<JobInterview> filteredJobInterviews = new ArrayList<>();

        for(JobInterview interview : jobInterviews){
            for(Application application : applications){
                if (interview.application().sameAs(application)) {
                    filteredJobInterviews.add(interview);
                }
            }
        }
        return filteredJobInterviews;
    }

    public List<JobOpening> findAllJobOpeningsWithInterviewModelAssigned() {
        List<JobOpening> allJobOpenings = (List<JobOpening>) jobOpeningRepository.findAll();
        List<JobOpening> filteredJobOpenings = new ArrayList<>();

        for(JobOpening jobOpening : allJobOpenings){
            if(jobOpening.myInterviewModel() != null){
                filteredJobOpenings.add(jobOpening);
            }
        }

        return filteredJobOpenings;
    }

    public List<Application> findAllApplicationsWithInterviewModel(List<JobOpening> jobOpeningsWithModel){
        List<Application> allApplications = (List<Application>) PersistenceContext.repositories().applications().findAll();
        List<Application> filteredApplications = new ArrayList<>();

        for(Application application : allApplications){
            for(JobOpening jobOpening : jobOpeningsWithModel){
                if(application.jobReference().sameReference(jobOpening.jobReference())){
                    filteredApplications.add(application);
                }
            }
        }
        return filteredApplications;
    }

    public InterviewModel getInterviewModelByJobInterviewID(int jobInterviewID) {
        Optional<JobInterview> jobInterview = jobInterviewRepository.ofIdentity(jobInterviewID);
        Application application = jobInterview.get().application();
        JobOpening jobOpening = application.jobReference();

        return jobOpening.myInterviewModel();
    }

    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();

        for (String line : lines) {
            if (!line.isEmpty()) {
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
        }

        return processedLines;
    }
}
