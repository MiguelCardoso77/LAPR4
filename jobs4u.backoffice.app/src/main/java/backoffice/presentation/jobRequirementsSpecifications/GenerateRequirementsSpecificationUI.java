package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.GenerateRequirementsSpecificationController;
import core.application.controllers.SelectJobOpeningController;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI for generating requirements specification templates for job openings.
 * This class interacts with the user to select a job opening and generates a requirements template based on the job's requirements specification.
 *
 * @author Tomás Gonçalves
 */
public class GenerateRequirementsSpecificationUI extends AbstractUI {
    private final GenerateRequirementsSpecificationController theController = new GenerateRequirementsSpecificationController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    /**
     * Displays the UI to generate requirements specifications for a job opening.
     *
     * @return true if the requirements template was generated successfully
     */
    @Override
    protected boolean doShow() {
        JobOpening jobOpening = selectJobOpeningController.selectJobOpening();

        List<String> model = theController.readFile(jobOpening.jobRequirementsSpecification().jobRequirementsPath());

        List<String> templateLines = theController.processLines(model);
        String jobReference = String.valueOf(jobOpening.jobReference());

        String fileName = jobReference + "-answerTemplate";
        theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/answeringTemplates/requirements/" + fileName + ".txt");

        return true;
    }

    /**
     * Returns the headline for this UI.
     *
     * @return the headline string
     */
    @Override
    public String headline() {
        return "Generate Requirements Template";
    }

}
