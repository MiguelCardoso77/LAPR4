package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the functionality to find the most referenced words in candidate curricula.
 * It creates an instance of the {@link MostReferencedWordsUI} class and invokes its `show()` method to initiate the functionality.
 * This action is triggered by user interaction or system events to perform the operation of finding the most referenced words.
 * Upon execution, it returns a boolean indicating the success or failure of the operation.
 *
 * @author Miguel Cardoso
 */
public class MostReferencedWordsAction implements Action {

    /**
     * Executes the functionality to find the most referenced words in candidate curricula.
     *
     * @return true if the operation is successfully executed, false otherwise.
     */
    @Override
    public boolean execute() {
        return new MostReferencedWordsUI().show();
    }
}
