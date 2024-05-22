package backoffice.presentation.jobRequirementsSpecifications;


import eapli.framework.actions.Action;

/**
 * @author 1220812@isep.ipp.pt
 */
public class VerificationRequirementsAction implements Action {
    @Override
    public boolean execute() {
        return new VerificationRequirementsUI().show();
    }
}
