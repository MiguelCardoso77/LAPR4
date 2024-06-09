package backoffice.presentation.jobRequirementsSpecifications;


import eapli.framework.actions.Action;

/**
 * This class represents an action that selects requirements specifications for job applications.
 * It implements the Action interface and is used to trigger the selection process
 * by displaying the SelectRequirementsSpecificationUI.
 *
 * @author Diogo Ribeiro
 */
public class SelectRequirementsSpecificationAction implements Action {

    /**
     * Executes the action to select the requirements specifications.
     * This method invokes the show method of SelectRequirementsSpecificationUI.
     *
     * @return true if the SelectRequirementsSpecificationUI was shown successfully, false otherwise
     */
    @Override
    public boolean execute() {
        return new SelectRequirementsSpecificationUI().show();
    }
}
