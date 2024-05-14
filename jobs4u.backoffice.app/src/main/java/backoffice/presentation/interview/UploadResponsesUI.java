package backoffice.presentation.interview;

import core.application.controllers.UploadResponsesController;
import core.domain.interview.JobInterview;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class UploadResponsesUI extends AbstractUI {
    private final UploadResponsesController theController = new UploadResponsesController();

    @Override
    protected boolean doShow() {
        int jobInterviewID = Console.readInteger("Enter the Job Interview ID: ");
        JobInterview jobInterview = theController.findInterviewByID(jobInterviewID);

        String path = Console.readLine("Enter the path to the file with the responses: ");
        path = "fileBot_OutputDirectory/IBM-000123/1/1-cv.txt";

        List<String> responses = theController.readFile(path);

        System.out.println(theController.uploadResponses(responses, jobInterview));

        return true;
    }

    @Override
    public String headline() {
        return "Upload Text File with Responses";
    }
}
