package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.repositories.JobInterviewRepository;
import core.repositories.JobOpeningRepository;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class RankCandidatesController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    public List<JobOpening> getAllJobOpenings() {
        return (List<JobOpening>) jobOpeningRepository.findAll();
    }

    public List<Application> getApplicationsForJobOpening(JobOpening jobOpening) {
        List<Application> applications = new ArrayList<>();

        for (Application a : applicationRepository.allApplications()) {
            if (a.jobReference().jobReference().equals(jobOpening.jobReference())) {
                applications.add(a);
            }
        }

        return applications;
    }

    public List<JobInterview> getInterviewsForApplication(Application application) {
        List<JobInterview> interviews = new ArrayList<>();

        for (JobInterview i : jobInterviewRepository.allJobInterviews()) {
            if (i.application().identity().equals(application.identity())) {
                interviews.add(i);
            }
        }

        return interviews;
    }
}
