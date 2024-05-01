package backoffice.presentation.authz;

import eapli.framework.actions.Action;

public class CollectDataFieldsAction implements Action {

    public boolean execute() {
        return new CollectDataFieldsUI().doShow();
    }

}
