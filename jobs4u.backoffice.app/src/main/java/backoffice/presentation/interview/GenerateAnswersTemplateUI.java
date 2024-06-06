package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.GenerateAnswersTemplateController;
import core.domain.interviewModel.InterviewModel;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


import java.util.List;

/**
 * This class is responsible for generating an answers template.
 * It allows the user to select a job opening that has an interview model assigned.
 * After the user selects a job opening, the corresponding interview model is retrieved.
 * The interview model is then processed to create an answers template, where everything after the second occurrence of ">" in each line is removed.
 * The answers template is saved to a file in the 'answeringTemplates' directory. The file name is based on the ID of the selected job interview.
 * This class does not handle the scoring of the questions or the total score of the interview model.
 *
 * @author Miguel Cardoso
 */
public class GenerateAnswersTemplateUI extends AbstractUI {
    private final GenerateAnswersTemplateController theController = new GenerateAnswersTemplateController();

    /**
     * Method responsible for displaying the UI for generating an answers template.
     * It first retrieves all job openings that have an interview model assigned and displays them.
     * The user is then prompted to choose an interview model by entering its ID.
     * The corresponding interview model for the chosen job interview is retrieved.
     *
     * @return true if the answers template is successfully generated and saved, false otherwise.
     */
    @Override
    protected boolean doShow() {
        Iterable<InterviewModel> interviews = theController.findAllInterviewModels();

        System.out.println("Interview Models Available: ");
        for (InterviewModel model : interviews) {
            System.out.println("ID: " + model.identity() + ", Model: " + model.model());
        }

        int selectedID = Console.readInteger("\nSelect an Interview Model: ");
        InterviewModel interviewModel = theController.findInterviewModelByID(selectedID);

        List<String> model = theController.readFile(interviewModel.model());
        List<String> templateLines = theController.processLines(model);

        String fileName = "interviewModel-id" + selectedID + "-answerTemplate";
        boolean writeFile = theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/answeringTemplates/interview/" + fileName + ".txt");

        if (writeFile) {
            System.out.println(ConsoleColors.GREEN + "Answers template generated successfully." + ConsoleColors.RESET);
            return false;
        } else {
            System.out.println(ConsoleColors.RED + "Error generating file." + ConsoleColors.RESET);
        }

        return true;
    }

    /**
     * Method to provide the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Generate Answers Template";
    }
}
