package backoffice.presentation.menus;

import backoffice.presentation.application.ListJobOpeningApplicationsAction;
import backoffice.presentation.application.UploadRequirementsAnswersAction;
import backoffice.presentation.candidate.*;
import backoffice.presentation.customer.RegisterCustomerAction;
import backoffice.presentation.interview.GenerateAnswersTemplateAction;
import backoffice.presentation.interview.JobInterviewEvaluationAction;
import backoffice.presentation.interview.SelectInterviewModelAction;
import backoffice.presentation.interview.UploadResponsesAction;
import backoffice.presentation.jobOpening.AddJobOpeningAction;
import backoffice.presentation.jobRequirementsSpecifications.SelectRequirementsSpecificationAction;
import backoffice.presentation.process.ChangeProcessStatusAction;
import backoffice.presentation.jobRequirementsSpecifications.VerificationRequirementsAction;
import backoffice.presentation.jobRequirementsSpecifications.GenerateRequirementsSpecificationAction;
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
    private static final int SELECT_INTERVIEW_MODEL = 7;
    private static final int GENERATE_REQUIREMENTS_SPECIFICATIONS = 8;
    private static final int SELECT_REQUIREMENTS_SPECIFICATIONS = 9;
    private static final int RANK_CANDIDATES = 10;
    private static final int UPLOAD_INTERVIEW_RESPONSES = 11;
    private static final int CHANGE_PROCESS_STATUS = 12;
    private static final int VERIFICATION_REQUIREMENTS = 13;
    private static final int INTERVIEWS_PROCESS_EVALUATION = 14;
    private static final int UPLOAD_CANDIDATE_REQUIREMENTS = 15;
    private static final int NOTIFY_CANDIDATES = 16;
    private static final int MOST_REFERENCED_WORDS = 17;


    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build() {
        final Menu menu = new Menu("Customer Manager Actions >");

        menu.addItem(DISPLAY_CANDIDATE_DATA, "Display Candidate Data", new DisplayCandidateDataAction());
        menu.addItem(LIST_ALL_APPLICATIONS_OF_JOB_OPENING, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );
        menu.addItem(REGISTER_CUSTOMER, "Register Customer", new RegisterCustomerAction());
        menu.addItem(REGISTER_CANDIDATE, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(ADD_JOB_OPENING, "Add Job Opening", new AddJobOpeningAction());
        menu.addItem(GENERATE_INTERVIEW_MODEL, "Generate Answers Template", new GenerateAnswersTemplateAction());
        menu.addItem(SELECT_INTERVIEW_MODEL, "Select Interview Model", new SelectInterviewModelAction());
        menu.addItem(GENERATE_REQUIREMENTS_SPECIFICATIONS, "Generate Requirements Specification", new GenerateRequirementsSpecificationAction());
        menu.addItem(SELECT_REQUIREMENTS_SPECIFICATIONS, "Select Requirements Specification", new SelectRequirementsSpecificationAction());
        menu.addItem(RANK_CANDIDATES, "Rank Candidates", new RankCandidatesAction());
        menu.addItem(UPLOAD_INTERVIEW_RESPONSES, "Upload Interview Responses", new UploadResponsesAction());
        menu.addItem(CHANGE_PROCESS_STATUS, "Change Process Status", new ChangeProcessStatusAction());
        menu.addItem(VERIFICATION_REQUIREMENTS, "Verify requirements of applications of a job  opening", new VerificationRequirementsAction());
        menu.addItem(INTERVIEWS_PROCESS_EVALUATION, "Execute the Interviews evaluation process", new JobInterviewEvaluationAction());
        menu.addItem(UPLOAD_CANDIDATE_REQUIREMENTS, "Upload the requirements of a candidate ", new UploadRequirementsAnswersAction());
        menu.addItem(NOTIFY_CANDIDATES, "Notify Candidates", new NotifyCandidatesAction());
        menu.addItem(MOST_REFERENCED_WORDS, "Most Referenced Words", new MostReferencedWordsAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Customer Manager Menu";
    }
}
