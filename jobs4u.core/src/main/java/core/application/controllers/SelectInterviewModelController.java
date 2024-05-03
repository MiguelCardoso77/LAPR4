package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import eapli.framework.application.UseCaseController;
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
            for (Application application : applicationList) {
                System.out.printf("%-6s%-30s%n", cont, application.candidate());
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
            for (JobInterview jobInterview : jobInterviews) {
                System.out.printf("%-6s%-30s%-30s%n", cont, jobInterview.interviewModel(), jobInterview.createdOn());
                cont++;
            }
        }
        return jobInterviews;
    }

    public List<String> listInterviewModels(){
        Path directory = Paths.get("jobs4u.core/src/main/resources/interviewModels");
        List<String> files = new ArrayList<>();
        try{
            files = Files.list(directory)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            files.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }
        return files;
    }

}
