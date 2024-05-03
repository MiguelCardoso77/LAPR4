package backoffice.presentation.candidate;

import eapli.framework.actions.Action;


/*
 * Action responsible for display candidate's data.
 *
 * @author Tomás Gonçalves
*/
public class DisplayCandidateDataAction implements Action {

    @Override
    public boolean execute() {
        return new DisplayCandidateDataUI().doShow();
    }


}
