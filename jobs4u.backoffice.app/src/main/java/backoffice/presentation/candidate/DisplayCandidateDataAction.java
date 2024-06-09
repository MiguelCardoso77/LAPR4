package backoffice.presentation.candidate;

import eapli.framework.actions.Action;


/**
 * Action responsible for displaying candidate's data.
 *
 * @author Tomás Gonçalves
 */
public class DisplayCandidateDataAction implements Action {

    /**
     * Method that executes the action.
     *
     * @return true if the action was successful, false otherwise
     */
    @Override
    public boolean execute() {
        return new DisplayCandidateDataUI().doShow();
    }

}
