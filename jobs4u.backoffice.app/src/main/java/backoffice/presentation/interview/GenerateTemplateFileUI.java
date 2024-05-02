package backoffice.presentation.interview;

import core.application.controllers.GenerateTemplateFileController;
import core.domain.interview.QuestionType;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class GenerateTemplateFileUI extends AbstractUI {
    private final GenerateTemplateFileController theController = new GenerateTemplateFileController();
    @Override
    protected boolean doShow() {
        int questions = Console.readInteger("Enter the number of questions you want in the template file: ");

        List<String> questionForFile = new ArrayList<>();
        String title = "# Questions:";
        questionForFile.add(title);

        while (questions > 0) {
            theController.displayQuestionTypes();
            int questionType = Console.readInteger("Chose the question type: ");
            String question = Console.readLine("Enter the question: ");
            String answer = Console.readLine("Enter the answer: ");
            int score = Console.readInteger("Enter the score: ");

            String endProduct = "-> " + QuestionType.values()[questionType - 1] + "<" + question + "> " + answer + " " + score + "%";
            questionForFile.add(endProduct);
            questions--;
        }

        String fileName = Console.readLine("Enter the name of the file: ");
        theController.writeListToFile(questionForFile, "jobs4u.core/src/main/resources/interviewModels/" + fileName + ".txt");

        return true;
    }

    @Override
    public String headline() {
        return "Generate Template File";
    }
}
