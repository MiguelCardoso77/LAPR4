package user.console.presentation.email;

import eapli.framework.actions.Action;

public class CheckEmailsAction implements Action {
    @Override
    public boolean execute() {
        return new CheckEmailsUI().show();
    }
}
