package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.GenerateInterviewModelController;
import core.domain.interview.QuestionType;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
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
     *         false otherwise.
     */
    @Override
    protected boolean doShow() {
        String ERROR_COLOR = ConsoleColors.RED;
        String RESET_COLOR = ConsoleColors.RESET;

        String telephoneNumber = Console.readLine("Enter the telephone number of the candidate: ");

        int questions = Console.readInteger("Enter the number of questions you want in the template file: ");
        int totalScore = 0;

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
            totalScore += score;

            String endProduct = "-> " + QuestionType.values()[questionType - 1] + "<" + question + "> " + answer + " " + score + "%";
            questionForFile.add(endProduct);
            questions--;
        }

        if (totalScore != 100) {
            System.out.println(ERROR_COLOR + "The total score of the questions is not 100%. Please review the questions." + RESET_COLOR);
            return false;
        }

        String fileName = telephoneNumber + "-InterviewModel";
        theController.writeListToFile(questionForFile, "jobs4u.core/src/main/resources/interviewModels/" + fileName + ".txt");

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
