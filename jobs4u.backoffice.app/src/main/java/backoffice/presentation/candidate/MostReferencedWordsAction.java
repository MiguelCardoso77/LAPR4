package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class MostReferencedWordsAction implements Action {
    @Override
    public boolean execute() {
        return new MostReferencedWordsUI().show();
    }
}
