package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import eapli.framework.io.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCaseController
public class SelectInterviewModelController {

    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    final ListJobOpeningApplicationsController jobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    final ListJobInterviewsApplicationController listJobInterviewsApplicationController = new ListJobInterviewsApplicationController();
    final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();
    final JobInterviewService service = new JobInterviewService();
    List<Application> applicationList = new ArrayList<>();
    Iterable<JobInterview> jobInterviews = new ArrayList<>();
    Iterable<JobOpening> iterable = jobOpeningController.allJobOpening();

    public Iterable<JobOpening> showJobOpenings() {
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings: \n");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
        }
        return iterable;
    }

    public Iterable<Application> showApplicationsOfJobOpening(JobOpening jobOpening) {
        applicationList = (List<Application>) jobOpeningApplicationsController.allApplicationsOfJobOpening(jobOpening.identity());
        if (applicationList.isEmpty()) {
            System.out.println("There are no Applications for this jobOpening");
        } else {
            int cont = 1;
            System.out.println("List of registered Applications: \n");
            System.out.printf("%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status", "Job Reference");

            for (Application application : applicationList) {
                System.out.printf("%-30s%-30s%-30s%-30s%n", cont, application.rank(), "Submitted",
                        application.jobReference().jobReference());
                cont++;
            }
        }
        return applicationList;
    }

    public Iterable<JobInterview> showInterviewsOfApplication(Application application) {
        jobInterviews = listJobInterviewsApplicationController.allJobInterviewsOfApplication(application);
        if (!jobInterviews.iterator().hasNext()) {
            System.out.println("There are no Job Interviews");
        } else {
            int cont = 1;
            System.out.println("List of registered Applications: \n");
            System.out.printf("%-30s%-30s%-30s%n", "Job Interview ID", "Interview Model", "Date");

            for (JobInterview jobInterview : jobInterviews) {
                System.out.printf("%-30s%-30s%-30s%n", cont, jobInterview.interviewModel(), jobInterview.createdOn().getTime());
                cont++;
            }
        }
        return jobInterviews;
    }

    public InterviewModel listAndSelectInterviewModels() {
        List<InterviewModel> interviewModels = (List<InterviewModel>) interviewModelRepository.findAll();
        int cont = 0;
        for (InterviewModel interviewModel : interviewModels) {
            System.out.println("id: " + cont + " - " + interviewModel);
            cont++;
        }
        int option;
        do {
            option = Console.readInteger("Enter the number of the file you want to select (0 to cancel): ");
            if (option < 0 || option > interviewModels.size()) {
                System.out.println("Invalid option. Please select a number between 1 and " + interviewModels.size() + ".");
            }
        } while (option < 0 || option > interviewModels.size());

        return (option == 0) ? null : interviewModels.get(option - 1);
    }

    public void updateInterviewModel(InterviewModel interviewModel, Integer id) {
        service.updateInterviewModel(interviewModel, id);
    }
}
