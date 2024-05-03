package backoffice.presentation.interview;

import eapli.framework.actions.Action;

/**
 * This class represents an action to generate an interview model.
 * It implements the {@link eapli.framework.actions.Action} interface
 * to define the execution behavior.
 */
public class GenerateInterviewModelAction implements Action {

    /**
     * Executes the action to generate an interview model.
     * It creates an instance of {@link GenerateInterviewModelUI} and
     * invokes its show() method to display the UI for generating
     * the interview model.
     *
     * @return true if the action is executed successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return new GenerateInterviewModelUI().show();
    }
}