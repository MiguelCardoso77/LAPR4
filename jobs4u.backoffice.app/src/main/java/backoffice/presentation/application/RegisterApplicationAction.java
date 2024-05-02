package backoffice.presentation.application;

import eapli.framework.actions.Action;

/**
 * @author 1220812@isep.ipp.pt
 */


public class RegisterApplicationAction implements Action {
    @Override
    public boolean execute(){
        return new RegisterApplicationUI().doShow();
    }
}