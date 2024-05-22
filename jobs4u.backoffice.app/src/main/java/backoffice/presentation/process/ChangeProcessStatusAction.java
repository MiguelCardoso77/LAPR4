package backoffice.presentation.process;

import eapli.framework.actions.Action;

public class ChangeProcessStatusAction implements Action {

    @Override
    public boolean execute() { return  new ChangeProcessStatusUI().show(); }
}
