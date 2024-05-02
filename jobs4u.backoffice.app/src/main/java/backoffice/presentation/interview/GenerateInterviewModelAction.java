package backoffice.presentation.interview;

import eapli.framework.actions.Action;

public class GenerateInterviewModelAction implements Action {
    @Override
    public boolean execute() {
        return new GenerateInterviewModelUI().show();
    }
}