package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
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
            System.out.printf("%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status",  "Job Reference" );

            for (Application application : applicationList) {
                System.out.printf("%-30s%-30s%-30s%-30s%n",cont, application.rank(), "Submitted",
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

    public String listAndSelectInterviewModels(){
        Path directory = Paths.get("jobs4u.core/src/main/resources/interviewModels");
        List<String> files;
        try {
            files = Files.list(directory)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            if (files.isEmpty()) {
                System.out.println("No files found.");
                return null;
            }
            System.out.println("List of files:");
            for (int i = 0; i < files.size(); i++) {
                System.out.println((i + 1) + ". " + files.get(i));
            }

            int option;
            do {
                option = Console.readInteger("Enter the number of the file you want to select (0 to cancel): ");
                if (option < 0 || option > files.size()) {
                    System.out.println("Invalid option. Please select a number between 1 and " + files.size() + ".");
                }
            } while (option < 0 || option > files.size());

            return (option == 0) ? null : files.get(option - 1);

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateInterviewModel(InterviewModel interviewModel, Integer id) {
        service.updateInterviewModel(interviewModel,id);
    }
}
