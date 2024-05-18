package backoffice.presentation.interview;

import core.application.controllers.GenerateAnswersTemplateController;
import core.application.controllers.UploadResponsesController;
import core.domain.interview.JobInterview;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class UploadResponsesUI extends AbstractUI {
    private final GenerateAnswersTemplateController generateAnswersTemplateController = new GenerateAnswersTemplateController();
    private final UploadResponsesController theController = new UploadResponsesController();

    @Override
    protected boolean doShow() {
        List<JobInterview> interviews = generateAnswersTemplateController.findAllInterviewsWithModelAssigned();

        System.out.println("Job Interviews: ");
        for (JobInterview jobInterview : interviews) {
            System.out.println("ID: " + jobInterview.identity() + " - " + jobInterview.interviewModel());
        }

        int jobInterviewID = Console.readInteger("Enter the Job Interview ID: ");
        JobInterview jobInterview = theController.findInterviewByID(jobInterviewID);

        String path = Console.readLine("\nEnter the path to the file with the responses: ");
        List<String> responses = theController.readFile(path);

        System.out.println(theController.uploadResponses(responses, jobInterview));

        return true;
    }

    @Override
    public String headline() {
        return "Upload Text File with Responses";
    }
}
