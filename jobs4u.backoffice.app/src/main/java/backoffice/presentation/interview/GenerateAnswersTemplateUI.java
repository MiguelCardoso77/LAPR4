package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.GenerateAnswersTemplateController;
import core.domain.interviewModel.InterviewModel;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI class responsible for generating an answers template for an interview model.
 * This class allows the user to select an interview model associated with a job opening.
 * It retrieves the selected interview model and processes it to create an answers template.
 * The answers template is then saved to a file in the 'answeringTemplates' directory.
 * The file name is based on the ID of the selected interview model.
 * This class does not handle the scoring of questions or the total score of the interview model.
 *
 * @author Miguel Cardoso
 */
public class GenerateAnswersTemplateUI extends AbstractUI {
    private final GenerateAnswersTemplateController theController = new GenerateAnswersTemplateController();

    /**
     * Displays the UI for generating an answers template.
     * It first retrieves all job openings that have an interview model assigned and displays them.
     * The user is prompted to choose an interview model by entering its ID.
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
     * Provides the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Generate Answers Template";
    }
}
