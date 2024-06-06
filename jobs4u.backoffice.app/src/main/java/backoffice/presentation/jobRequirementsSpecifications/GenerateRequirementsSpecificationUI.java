package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.GenerateRequirementsSpecificationController;
import core.application.controllers.SelectJobOpeningController;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class GenerateRequirementsSpecificationUI extends AbstractUI {
    private final GenerateRequirementsSpecificationController theController = new GenerateRequirementsSpecificationController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    @Override
    protected boolean doShow() {
        List<JobOpening> allJobOpening = theController.findAllJobOpeningAssigned();

        System.out.println("Job Opening: ");
        for (JobOpening jobOpening : allJobOpening) {
            System.out.println("ID: " + jobOpening.identity() + " - " + jobOpening.jobRequirementsSpecification());
        }

        JobOpening jobOpening = selectJobOpeningController.selectJobOpening();

        List<String> model = theController.readFile(jobOpening.jobRequirementsSpecification().jobRequirementsPath());
        List<String> templateLines = theController.processLines(model);
        String jobReference = String.valueOf(jobOpening.jobReference());

        String fileName = jobReference + "-answerTemplate";
        theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/answeringTemplates/requirements/" + fileName + ".txt");

        return true;

    }

    @Override
    public String headline() {
        return "Generate Template File";
    }
}
