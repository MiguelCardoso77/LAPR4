package backoffice.presentation.interview;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the upload responses UI.
 * It creates an instance of UploadResponsesUI and invokes its show method.
 *
 * @author Miguel Cardoso
 */
public class UploadResponsesAction implements Action {

    /**
     * Method that creates an instance of UploadResponsesUI and invokes its show method.
     *
     * @return boolean
     */
    @Override
    public boolean execute() {
        return new UploadResponsesUI().show();
    }
}
