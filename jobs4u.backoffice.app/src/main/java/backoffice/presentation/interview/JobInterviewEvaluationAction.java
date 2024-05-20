package backoffice.presentation.interview;

import eapli.framework.actions.Action;

/**
 * This class represents an action for evaluating a job interview.
 * It implements the {@link eapli.framework.actions.Action} interface,
 * which requires implementing the {@code execute} method.
 * When the {@code execute} method is called, it triggers the display
 * of the {@link JobInterviewEvaluationUI} user interface for evaluating
 * job interviews.
 *
 * @author 1220812@isep.ipp.pt
 */

public class JobInterviewEvaluationAction implements Action {
    /**
     * Executes the action to show the job interview evaluation user interface.
     *
     * @return a boolean indicating whether the UI was successfully shown
     */
    @Override
    public boolean execute(){
        return new JobInterviewEvaluationUI().show();
    }
}
