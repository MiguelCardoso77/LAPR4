package backoffice.presentation.menus;

//import backoffice.presentation.application.ListJobOpeningApplicationsAction;
import backoffice.presentation.candidate.DisplayCandidateDataAction;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.customer.RegisterCustomerAction;
import backoffice.presentation.interview.GenerateInterviewModelAction;
import backoffice.presentation.jobOpening.AddJobOpeningAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

public class CustomerManagerMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;
    private static final int DISPLAY_CANDIDATE_DATA = 1;
    private static final int LIST_ALL_APPLICATIONS_OF_JOB_OPENING = 2;
    private static final int REGISTER_CUSTOMER = 3;
    private static final int REGISTER_CANDIDATE = 4;
    private static final int ADD_JOB_OPENING = 5;
    private static final int GENERATE_INTERVIEW_MODEL = 6;

    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build() {
        final Menu menu = new Menu("Customer Manager Actions >");

        menu.addItem(DISPLAY_CANDIDATE_DATA, "Display Candidate Data", new DisplayCandidateDataAction());
        //menu.addItem(LIST_ALL_APPLICATIONS_OF_JOB_OPENING, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );

        menu.addItem(REGISTER_CUSTOMER, "Register Customer", new RegisterCustomerAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(ADD_JOB_OPENING, "Add Job Opening", new AddJobOpeningAction());

        menu.addItem(GENERATE_INTERVIEW_MODEL, "Generate Interview Model", new GenerateInterviewModelAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Customer Manager Menu";
    }
}
