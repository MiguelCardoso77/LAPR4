package backoffice.presentation.menus;

import backoffice.presentation.authz.ListUsersAction;
import backoffice.presentation.candidate.ListCandidateApplicationsAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

public class CandidateMenu extends AbstractUI {


    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final int LIST_ALL_MY_APPLICATIONS = 1;


    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build() {
        final Menu menu = new Menu("Candidate Actions >");

        menu.addItem(LIST_ALL_MY_APPLICATIONS, "List all my Applications", new ListCandidateApplicationsAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Candidate Menu";
    }


}
