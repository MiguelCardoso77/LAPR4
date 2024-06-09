package core.application.controllers;

import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.JobReference;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import core.services.JobOpeningService;
import eapli.framework.application.UseCaseController;
import eapli.framework.io.util.Console;

import java.util.List;

/**
 * Controller for selecting an interview model in the Jobs4U system.
 * This class provides methods to list and select interview models, and to update the interview model of a job opening.
 * It uses the InterviewModelRepository and JobOpeningService from the core services.
 *
 * @author Diana Neves
 */
@UseCaseController
public class SelectInterviewModelController {
    final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();
    final JobOpeningService jobOpeningService = new JobOpeningService();
    /**
     * Lists all interview models and allows the user to select one.
     *
     * @return the selected interview model
     */
    public InterviewModel listAndSelectInterviewModels() {
        List<InterviewModel> interviewModels = (List<InterviewModel>) interviewModelRepository.findAll();
        int cont = 1;
        for (InterviewModel interviewModel : interviewModels) {
            System.out.println("ID: " + cont + " - " + interviewModel);
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
    /**
     * Updates the interview model of a job opening.
     *
     * @param jobReference the reference of the job opening
     * @param interviewModel the new interview model
     */
    public void updateInterviewModel(JobReference jobReference, InterviewModel interviewModel) {
        jobOpeningService.updateInterviewModel(jobReference, interviewModel);
    }
}
