package backoffice.presentation.jobRequirementsSpecifications;

import eapli.framework.actions.Action;

/**
 * Action class to execute the generation of requirements specifications for job openings.
 * This class implements the Action interface and triggers the display of the UI for generating requirements specifications.
 *
 * @author Tomás Gonçalves
 */
public class GenerateRequirementsSpecificationAction implements Action {

    /**
     * Executes the action of generating requirements specifications for job openings.
     *
     * @return true if the generation of requirements specifications is successful, false otherwise
     */
    @Override
    public boolean execute() {
        return new GenerateRequirementsSpecificationUI().show();
    }

}
