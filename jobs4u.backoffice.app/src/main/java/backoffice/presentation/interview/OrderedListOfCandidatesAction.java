package backoffice.presentation.interview;

import eapli.framework.actions.Action;

public class OrderedListOfCandidatesAction implements Action {

    @Override
    public boolean execute(){
        return new OrderedListOfCandidatesUI().doShow();
    }
}
