package backoffice.presentation.company;


import eapli.framework.actions.Action;

public class ListCompaniesAction implements Action {

    @Override
    public boolean execute() {
        return new ListCompaniesUI().doShow();
    }
}
