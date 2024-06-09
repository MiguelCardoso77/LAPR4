package backoffice.presentation.menus;

import backoffice.presentation.application.RegisterApplicationAction;
import backoffice.presentation.application.UploadRequirementsAnswersAction;
import backoffice.presentation.authz.ListUsersAction;
import backoffice.presentation.candidate.ListCandidatesAction;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.jobRequirementsSpecifications.GenerateRequirementsSpecificationAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

/**
 * The OperatorMenu class represents the menu for operator actions
 * in the Jobs4u Back Office application.
 * It extends the AbstractUI class and provides a menu with various options
 * specific to the operator role.
 *
 * @author Miguel Cardoso
 * @see AbstractUI
 * @see Menu
 * @see Actions
 */
public class OperatorMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final int LIST_ALL_USERS = 1;
    private static final int LIST_ALL_CANDIDATES = 2;
    private static final int REGISTER_CANDIDATE = 3;
    private static final int REGISTER_APPLICATION = 4;
    private static final int COLLECT_DATA_FIELDS = 5;
    private static final int UPLOAD_REQUIREMENTS = 6;

    /**
     * This method is overridden to always return true as this UI does not perform any specific actions.
     *
     * @return true
     */
    @Override
    protected boolean doShow() {
        return true;
    }

    /**
     * Builds the menu for operator actions.
     *
     * @return the constructed menu with operator actions
     */
    public Menu build() {
        final Menu menu = new Menu("Operator Actions >");

        menu.addItem(LIST_ALL_USERS, "List all Users", new ListUsersAction());
        menu.addItem(LIST_ALL_CANDIDATES, "List all Candidates", new ListCandidatesAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(REGISTER_APPLICATION, "Register Application", new RegisterApplicationAction());
        menu.addItem(COLLECT_DATA_FIELDS, "Collect data fields for candidates of a job opening", new GenerateRequirementsSpecificationAction());
        menu.addItem(UPLOAD_REQUIREMENTS, "Upload the requirements of a candidate ", new UploadRequirementsAnswersAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    /**
     * Returns the headline for the operator menu.
     *
     * @return the headline string
     */
    @Override
    public String headline() {
        return "Operator Menu";
    }
}
