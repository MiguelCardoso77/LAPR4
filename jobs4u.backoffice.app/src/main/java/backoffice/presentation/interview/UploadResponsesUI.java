package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.UploadResponsesController;
import core.domain.interview.JobInterview;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI class responsible for uploading text files with interview responses.
 * It allows the user to select a job interview from a list and enter the path to the file containing the responses.
 * The responses are then retrieved from the file and uploaded for the selected job interview.
 *
 * @author Miguel Cardoso
 */
public class UploadResponsesUI extends AbstractUI {
    private final UploadResponsesController theController = new UploadResponsesController();

    /**
     * Method to display the UI for uploading responses.
     *
     * @return true if the responses are uploaded successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        List<JobInterview> interviews = theController.findAllInterviewsWithModelAssigned();

        System.out.println("Job Interviews: ");
        for (JobInterview jobInterview : interviews) {
            System.out.println("ID: " + jobInterview.identity() + ", from application " + jobInterview.application().dataFile());
        }

        int jobInterviewID = Console.readInteger("Enter the Job Interview ID: ");
        JobInterview jobInterview = theController.findInterviewByID(jobInterviewID);

        String path = Console.readLine("\nEnter the path to the file with the responses: ");
        List<String> responses = theController.retrieveResponses(path);

        theController.uploadResponses(responses, jobInterview);
        System.out.println(ConsoleColors.GREEN + "Responses uploaded successfully!" + ConsoleColors.RESET);

        return true;
    }

    /**
     * Method to provide the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Upload Text File with Responses";
    }
}
