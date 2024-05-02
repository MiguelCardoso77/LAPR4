package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.GenerateInterviewModelController;
import core.domain.interview.QuestionType;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class GenerateInterviewModelUI extends AbstractUI {
    private final GenerateInterviewModelController theController = new GenerateInterviewModelController();


    @Override
    protected boolean doShow() {
        String ERROR_COLOR = ConsoleColors.RED;
        String RESET_COLOR = ConsoleColors.RESET;

        int questions = Console.readInteger("Enter the number of questions you want in the template file: ");

        List<String> questionForFile = new ArrayList<>();
        String title = "# Questions:";
        questionForFile.add(title);

        while (questions > 0) {
            theController.displayQuestionTypes();
            int questionType = Console.readInteger("Chose the question type: ");
            String question = Console.readLine("Enter the question: ");

            String answer = "";
            switch (questionType) {
                case 1: // DATE
                    answer = theController.dateAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 2: // DECIMAL_NUMBER
                    answer = theController.decimalNumberAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 3: // INTEGER_NUMBER
                    answer = theController.integerNumberAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 4: // MULTIPLE_CHOICE
                    answer = theController.multipleChoiceAnswer(question, ERROR_COLOR, RESET_COLOR);
                    break;
                case 5: // NUMERIC_RANGE
                    answer = theController.numericRangeAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 6: // SINGLE_CHOICE
                    answer = theController.singleChoiceAnswer(question, answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 7: // SHORT_TEXT
                    answer = theController.shortTextAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 8: // TIME
                    answer = theController.timeAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
                case 9: // TRUE_FALSE
                    answer = theController.trueFalseAnswer(answer, ERROR_COLOR, RESET_COLOR);
                    break;
            }

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
