package backoffice.presentation.jobRequirementsSpecifications;


import eapli.framework.actions.Action;

/**
 * This class represents an action that verifies the requirements of job applications.
 * It implements the Action interface and is used to trigger the verification process
 * by displaying the VerificationRequirementsUI.
 *
 * @author Tomás Gonçalves
 */
public class VerificationRequirementsAction implements Action {

    /**
     * Executes the action to verify the requirements.
     * This method invokes the show method of VerificationRequirementsUI.
     *
     * @return true if the VerificationRequirementsUI was shown successfully, false otherwise
     */
    @Override
    public boolean execute() {
        return new VerificationRequirementsUI().show();
    }
}
