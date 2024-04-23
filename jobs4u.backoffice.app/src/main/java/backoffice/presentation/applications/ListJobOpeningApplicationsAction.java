package backoffice.presentation.applications;

import eapli.framework.actions.Action;


    public class ListJobOpeningApplicationsAction implements Action  {

        @Override
        public boolean execute() {
            return new ListJobOpeningApplicationsUI().doShow();
        }
    }




