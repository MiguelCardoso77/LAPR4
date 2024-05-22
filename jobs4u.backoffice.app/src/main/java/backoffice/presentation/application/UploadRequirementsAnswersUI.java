package backoffice.presentation.application;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class UploadRequirementsAnswersUI extends AbstractUI {
    final GenerateRequirementsAnswersTemplateController generateRequirementsAnswersTemplateController = new GenerateRequirementsAnswersTemplateController();
    final UploadRequirementsAnswersController uploadRequirementsAnswersController = new UploadRequirementsAnswersController();
    final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    Iterable<Application> applications = new ArrayList<>();

    @Override
    protected boolean doShow() {
        List<JobOpening> requirements = generateRequirementsAnswersTemplateController.findAllJobOpeningsWithJobRequirements();

        int cont = 1;
        if (requirements.isEmpty()) {
            System.out.println("there are no job openings with requirements");
        } else {
            System.out.println("Job Opening: ");
            for (JobOpening jobOpening : requirements) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
            JobOpening jobOpening = selectJobOpeningController.selectorPart(requirements);

            applications = listJobOpeningApplicationsController.showApplicationsOfJobOpening(jobOpening.jobReference());
            Application application = listJobOpeningApplicationsController.selectApplication();

            String path = Console.readLine("\nEnter the path to the file with the responses: ");
            List<String> responses = uploadRequirementsAnswersController.readFile(path);

            uploadRequirementsAnswersController.uploadResponses(responses, application);

        }
        return true;
    }


    @Override
    public String headline() {
        return "Upload a text file with the requirements of a candidate for its verification";
    }
}
