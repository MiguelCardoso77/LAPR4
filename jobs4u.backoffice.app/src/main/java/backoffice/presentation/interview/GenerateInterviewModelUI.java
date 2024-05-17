package backoffice.presentation.interview;

import core.application.controllers.GenerateInterviewModelController;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * This class is responsible for generating an interview model UI.
 * It allows the user to enter the number of questions they want in the template file,
 * choose the question type, enter the question, and enter the score for each question.
 * The total score of the questions should be 100%.
 * If the total score is not 100%, it will print an error message and return false.
 * If the total score is 100%, it will ask the user to enter the name of the file,
 * and write the questions to the file.
 *
 * @author Miguel Cardoso
 */
public class GenerateInterviewModelUI extends AbstractUI {
    private final GenerateInterviewModelController theController = new GenerateInterviewModelController();

    /**
     * Method responsible for displaying the UI for generating an interview model.
     * It prompts the user to enter the number of questions, their types, content,
     * and scores. It ensures that the total score of all questions sums up to 100%.
     * If the total score does not reach 100%, an error message is displayed and
     * the method returns false. If the total score is 100%, the user is prompted
     * to enter the name of the file where the questions will be saved.
     *
     * @return true if the interview model is successfully generated and saved,
     * false otherwise.
     */
    @Override
    protected boolean doShow() {
        List<JobInterview> allJobInterviews = theController.findAllInterviews();

        System.out.println("Job Interviews: ");
        for (JobInterview jobInterview : allJobInterviews) {
            System.out.println("ID: " + jobInterview.identity() + " - " + jobInterview.interviewModel());
        }

        int jobInterviewID = Console.readInteger("Choose a Job Interview: ");
        InterviewModel interviewModel = theController.getInterviewModelByJobInterviewID(jobInterviewID);

        List<String> model = theController.readFile(interviewModel.model());
        List<String> templateLines = theController.processLines(model);


        String fileName = Console.readLine("Enter the name of the file: ");
        theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/answeringTemplates/" + fileName + ".txt");

        return true;
    }

    /**
     * Method to provide the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Generate Template File";
    }
}
