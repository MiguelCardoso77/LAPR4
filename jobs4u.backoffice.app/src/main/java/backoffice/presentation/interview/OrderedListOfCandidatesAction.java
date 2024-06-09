package backoffice.presentation.interview;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the UI to display an ordered list of candidates.
 * It invokes the UI for presenting the ordered list of candidates based on interview grades.
 *
 * @author Tomás Gonçalves
 */
public class OrderedListOfCandidatesAction implements Action {

    /**
     * Executes the action to display the ordered list of candidates.
     *
     * @return true if the action is successfully executed, false otherwise.
     */
    @Override
    public boolean execute(){
        return new OrderedListOfCandidatesUI().doShow();
    }
}
