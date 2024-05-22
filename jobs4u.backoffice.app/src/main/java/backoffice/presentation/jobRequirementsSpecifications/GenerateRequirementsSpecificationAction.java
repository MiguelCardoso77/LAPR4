package backoffice.presentation.jobRequirementsSpecifications;

import eapli.framework.actions.Action;

public class GenerateRequirementsSpecificationAction implements Action {

    @Override
    public boolean execute() {
        return new GenerateRequirementsSpecificationUI().show();
    }

}
