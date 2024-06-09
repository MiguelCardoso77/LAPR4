package backoffice.presentation.application;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;

/**
 * UI class responsible for handling the upload of requirement answers for a candidate's application.
 * This UI allows back office users to upload requirement answers for a candidate's application.
 *
 * @author Diana Neves
 */
public class UploadRequirementsAnswersUI extends AbstractUI {
    final UploadRequirementsAnswersController uploadRequirementsAnswersController = new UploadRequirementsAnswersController();

    /**
     * Displays the UI for uploading candidate requirements answers.
     *
     * @return true if the requirement answers were successfully uploaded, false otherwise
     */
    @Override
    protected boolean doShow() {
        List<JobOpening> requirements = uploadRequirementsAnswersController.findAllJobOpeningsWithJobRequirements();

        if (requirements.isEmpty()) {
            System.out.println(ConsoleColors.RED + "There are no job openings with requirements" + ConsoleColors.RESET);
            return false;
        } else {
            showJobOpeningsWithRequirements(requirements);

            JobOpening jobOpening = uploadRequirementsAnswersController.selectJobOpening(requirements);
            uploadRequirementsAnswersController.showApplicationsOfJobOpening(jobOpening.jobReference());
            Application application = uploadRequirementsAnswersController.selectApplication();

            String path = Console.readLine("\nEnter the path to the file with the requirements: ");
            List<String> candidateRequirements = uploadRequirementsAnswersController.retrieveResponseRequirements(path);

            if (!candidateRequirements.isEmpty()){
                uploadRequirementsAnswersController.uploadRequirements(candidateRequirements, application);
                System.out.println(ConsoleColors.GREEN + " Candidate Requirements uploaded successfully!" + ConsoleColors.RESET);
            } else{
                System.out.println("Invalid Path!");
            }

            return true;
        }
    }

    /**
     * Displays the list of job openings with their respective requirements.
     *
     * @param requirements the list of job openings with requirements
     */
    public void showJobOpeningsWithRequirements(List<JobOpening> requirements){
        System.out.printf("%-30s%-30s%-30s%-30s%n", "Job Opening Number:", "Job Reference:", "Title or Function:", "Job Opening Customer:");
        int cont = 1;

        for (JobOpening jobOpening : requirements) {
            System.out.printf("%-30s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.customer());
            cont++;
        }
    }

    /**
     * Provides the headline for this UI.
     *
     * @return the headline string
     */
    @Override
    public String headline() {
        return "Upload a text file with the requirements of a candidate for its verification";
    }
}
