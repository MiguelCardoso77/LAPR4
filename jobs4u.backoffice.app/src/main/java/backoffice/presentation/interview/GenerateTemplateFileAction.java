package backoffice.presentation.interview;

import eapli.framework.actions.Action;

public class GenerateTemplateFileAction implements Action {
    @Override
    public boolean execute() {
        return new GenerateTemplateFileUI().show();
    }
}