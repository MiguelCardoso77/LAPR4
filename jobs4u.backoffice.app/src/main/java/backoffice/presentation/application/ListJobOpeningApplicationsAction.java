package backoffice.presentation.application;

import eapli.framework.actions.Action;

/**
 * Action responsible for listing job opening applications.
 *
 * @author Tomás Gonçalves
 */

    public class ListJobOpeningApplicationsAction implements Action  {

/**
     * Executes the action to list job opening applications.
     *
     * @return true if the action is executed successfully, otherwise false.
 */
        @Override
        public boolean execute() {
            return new ListJobOpeningApplicationsUI().doShow();
        }
    }






