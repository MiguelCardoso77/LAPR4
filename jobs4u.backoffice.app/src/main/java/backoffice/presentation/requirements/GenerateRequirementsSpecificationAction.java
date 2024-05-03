package backoffice.presentation.requirements;

import backoffice.presentation.interview.GenerateInterviewModelUI;
import eapli.framework.actions.Action;

import javax.swing.*;

public class GenerateRequirementsSpecificationAction implements Action {

    @Override
    public boolean execute() {
        return new GenerateRequirementsSpecificationUI().show();
    }

}
