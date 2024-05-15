package backoffice.presentation.menus;

import backoffice.presentation.application.RegisterApplicationAction;
import backoffice.presentation.authz.ListUsersAction;
import backoffice.presentation.candidate.ListCandidatesAction;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.requirements.GenerateRequirementsSpecificationAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

public class OperatorMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final int LIST_ALL_USERS = 1;
    private static final int LIST_ALL_CANDIDATES = 2;
    private static final int REGISTER_CANDIDATE = 3;
    private static final int REGISTER_APPLICATION = 4;
    private static final int COLLECT_DATA_FIELDS = 5;


    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build() {
        final Menu menu = new Menu("Operator Actions >");

        menu.addItem(LIST_ALL_USERS, "List all Users", new ListUsersAction());
        menu.addItem(LIST_ALL_CANDIDATES, "List all Candidates", new ListCandidatesAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(REGISTER_APPLICATION, "Register Application", new RegisterApplicationAction());
        menu.addItem(COLLECT_DATA_FIELDS, "Collect data fields for candidates of a job opening", new GenerateRequirementsSpecificationAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Operator Menu";
    }
}
