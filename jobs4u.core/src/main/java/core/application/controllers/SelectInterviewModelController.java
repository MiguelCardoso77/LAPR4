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

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class SelectInterviewModelController {

    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    final ListJobOpeningApplicationsController jobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();
    final JobInterviewService service = new JobInterviewService();
    List<Application> applicationList = new ArrayList<>();


    public Iterable<Application> applicationsOfJobOpening(JobOpening jobOpening) {
        return applicationList = (List<Application>) jobOpeningApplicationsController.allApplicationsOfJobOpening(jobOpening.identity());
    }

    public InterviewModel listAndSelectInterviewModels() {
        List<InterviewModel> interviewModels = (List<InterviewModel>) interviewModelRepository.findAll();
        int cont = 1;
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
