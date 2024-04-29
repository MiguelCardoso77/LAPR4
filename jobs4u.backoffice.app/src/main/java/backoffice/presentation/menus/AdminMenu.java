package backoffice.presentation.menus;

import backoffice.presentation.application.ListJobOpeningApplicationsAction;
import backoffice.presentation.authz.*;
import backoffice.presentation.candidate.DisplayCandidateDataAction;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.customer.RegisterCustomerAction;
import backoffice.presentation.jobOpening.AddJobOpeningAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

public class AdminMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final int LIST_ALL_USERS = 1;
    private static final int LIST_ALL_BACKOFFICE_USERS = 2;
    private static final int DISPLAY_CANDIDATE_DATA = 3;
    private static final int LIST_ALL_APPLICATIONS_OF_JOB_OPENING = 4;
    private static final int ADD_USER = 5;
    private static final int ACTIVATE_USER = 6;
    private static final int DEACTIVATE_USER = 7;
    private static final int REGISTER_CUSTOMER = 8;
    private static final int REGISTER_CANDIDATE = 9;
    private static final int ADD_JOB_OPENING = 10;

    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build() {
        final Menu menu = new Menu("Admin Actions >");

        menu.addItem(LIST_ALL_USERS, "List all Users", new ListUsersAction());
        menu.addItem(LIST_ALL_BACKOFFICE_USERS, "List all Backoffice Users", new ListBackofficeUsersAction());
        menu.addItem(DISPLAY_CANDIDATE_DATA, "Display Candidate Data", new DisplayCandidateDataAction());
        menu.addItem(LIST_ALL_APPLICATIONS_OF_JOB_OPENING, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );

        menu.addItem(ADD_USER, "Add User", new AddUserUI()::show);
        menu.addItem(ACTIVATE_USER,"Activate User", new ActivateUserAction());
        menu.addItem(DEACTIVATE_USER, "Deactivate User", new DeactivateUserAction());

        menu.addItem(REGISTER_CUSTOMER, "Register Customer", new RegisterCustomerAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(ADD_JOB_OPENING, "Add Job Opening", new AddJobOpeningAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Admin Menu";
    }

}
