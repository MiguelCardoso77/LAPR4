package backoffice.presentation.application;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;

public class UploadRequirementsAnswersUI extends AbstractUI {
    final UploadRequirementsAnswersController uploadRequirementsAnswersController = new UploadRequirementsAnswersController();
    final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    @Override
    protected boolean doShow() {
        List<JobOpening> requirements = uploadRequirementsAnswersController.findAllJobOpeningsWithJobRequirements();

        int cont = 1;
        if (requirements.isEmpty()) {
            System.out.println(ConsoleColors.RED + "There are no job openings with requirements" + ConsoleColors.RESET);
            return false;
        } else {
            System.out.printf("%-30s%-30s%-30s%-30s%n", "Job Opening Number:", "Job Reference:", "Title or Function:", "Job Opening Customer:");

            for (JobOpening jobOpening : requirements) {
                System.out.printf("%-30s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.customer());
                cont++;
            }

            JobOpening jobOpening = selectJobOpeningController.selectorPart(requirements);
            listJobOpeningApplicationsController.showApplicationsOfJobOpening(jobOpening.jobReference());
            Application application = listJobOpeningApplicationsController.selectApplication();

            String path = Console.readLine("\nEnter the path to the file with the requirements: ");
            List<String> candidateRequirements = uploadRequirementsAnswersController.retrieveResponseRequirements(path);

            uploadRequirementsAnswersController.uploadRequirements(candidateRequirements, application);
            System.out.println(ConsoleColors.GREEN + " Candidate Requirements uploaded successfully!" + ConsoleColors.RESET);

            return true;
        }
    }


    @Override
    public String headline() {
        return "Upload a text file with the requirements of a candidate for its verification";
    }
}
