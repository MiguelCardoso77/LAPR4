package backoffice.presentation.authz;

import core.application.controllers.CollectDataFieldsController;
import eapli.framework.presentation.console.AbstractUI;

public class CollectDataFieldsUI extends AbstractUI {

    private final CollectDataFieldsController theController = new CollectDataFieldsController();


    @Override
    public String headline() {
        return "Collect data fields for candidates of a job opening";
    }


    @Override
    protected boolean doShow() {



        return true;
    }


}

