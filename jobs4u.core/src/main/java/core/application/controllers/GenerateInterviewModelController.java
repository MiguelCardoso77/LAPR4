package core.application.controllers;

import core.domain.interview.QuestionType;
import eapli.framework.io.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateInterviewModelController {
    public List<String> getQuestionTypes() {
        List<String> questionTypes = new ArrayList<>();
        for (QuestionType type : QuestionType.values()) {
            questionTypes.add(type.toString());
        }
        return questionTypes;
    }

    public void displayQuestionTypes() {
        List<String> questionTypes = getQuestionTypes();
        System.out.println("Choose the question type:");
        for (int i = 0; i < questionTypes.size(); i++) {
            System.out.println((i + 1) + ". " + questionTypes.get(i));
        }
    }

    public void writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file.");
        }
    }

    public String dateAnswer(String answer, String errorColor, String resetColor) {
        boolean validDate = false;

        while (!validDate) {
            answer = Console.readLine("Enter the date (DD/MM/YYYY): ");
            try {
                LocalDate.parse(answer, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println(errorColor + "Invalid date format. Please enter the date in the format DD/MM/YYYY." + resetColor);
            }
        }

        return answer;
    }

    public String decimalNumberAnswer(String answer, String errorColor, String resetColor) {
        boolean validDecimal = false;

        while (!validDecimal) {
            answer = Console.readLine("Enter the decimal number: ");
            try {
                Double.parseDouble(answer);
                validDecimal = true;
            } catch (NumberFormatException e) {
                System.out.println(errorColor + "Invalid decimal number. Please enter a valid decimal number." + resetColor);
            }
        }

        return answer;
    }

    public String integerNumberAnswer(String answer, String errorColor, String resetColor) {
        boolean validInteger = false;

        while (!validInteger) {
            answer = Console.readLine("Enter the integer number: ");
            try {
                Integer.parseInt(answer);
                validInteger = true;
            } catch (NumberFormatException e) {
                System.out.println(errorColor + "Invalid integer number. Please enter a valid integer number." + resetColor);
            }
        }

        return answer;
    }

    public String multipleChoiceAnswer(String question, String errorColor, String resetColor) {
        boolean validChoices = false;
        String[] possibleAnswers = null;

        // Extract the possible answers from the question
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(question);
        if (matcher.find()) {
            possibleAnswers = matcher.group(1).split(", ");
        }

        List<String> answers = new ArrayList<>();
        while (!validChoices) {
            String input = Console.readLine("Enter the multiple choice answers (separated by spaces): ");
            String[] inputAnswers = input.split(" ");

            for (String answer : inputAnswers) {
                if (answer.matches("<.*>")) {
                    // Remove the angle brackets from the answer
                    answer = answer.substring(1, answer.length() - 1);

                    // Check if the answer is one of the possible answers
                    if (Arrays.asList(possibleAnswers).contains(answer)) {
                        answers.add("<" + answer + ">");
                    } else {
                        System.out.println(errorColor + "Invalid choice: " + answer + ". Please enter valid choices." + resetColor);
                        answers.clear();
                        break;
                    }
                } else {
                    System.out.println(errorColor + "Invalid format for: " + answer + ". Please enter the answers within < >." + resetColor);
                    answers.clear();
                    break;
                }

                if (answers.size() == inputAnswers.length) {
                    validChoices = true;
                }
            }
        }

        return String.join(" ", answers);
    }

    public String numericRangeAnswer(String answer, String errorColor, String resetColor) {
        boolean validNumericRange = false;

        while (!validNumericRange) {
            answer = Console.readLine("Enter the numeric range: ");
            if (answer.matches("\\d+-\\d+")) {
                try {
                    String[] numbers = answer.split("-");
                    Integer.parseInt(numbers[0]);
                    Integer.parseInt(numbers[1]);
                    validNumericRange = true;
                } catch (NumberFormatException e) {
                    System.out.println(errorColor + "Invalid numeric range. Please enter a valid numeric range (x-x)." + resetColor);
                }
            } else {
                System.out.println(errorColor + "Invalid format. Please enter the numeric range in the format x-x." + resetColor);
            }
        }

        return answer;
    }

    public String singleChoiceAnswer(String question, String answer, String errorColor, String resetColor) {
        boolean validChoice = false;
        String[] possibleAnswers = null;

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(question);
        if (matcher.find()) {
            possibleAnswers = matcher.group(1).split(", ");
        }

        while (!validChoice) {
            answer = Console.readLine("Enter the single choice answer: ");
            if (answer.matches("<.*>")) {
                answer = answer.substring(1, answer.length() - 1);

                if (Arrays.asList(possibleAnswers).contains(answer)) {
                    validChoice = true;
                } else {
                    System.out.println(errorColor + "Invalid choice. Please enter a valid choice." + resetColor);
                }
            } else {
                System.out.println(errorColor + "Invalid format. Please enter the answer within < >." + resetColor);
            }
        }

        return "<" + answer + ">";
    }

    public String shortTextAnswer(String answer, String errorColor, String resetColor) {
        boolean validText = false;

        while (!validText) {
            answer = Console.readLine("Enter the text within < >: ");
            if (answer.matches("<.*>")) {
                validText = true;
            } else {
                System.out.println(errorColor + "Invalid format. Please enter the text within < >." + resetColor);
            }
        }

        return answer;
    }

    public String timeAnswer(String answer, String errorColor, String resetColor) {
        boolean validTime = false;

        while (!validTime) {
            answer = Console.readLine("Enter the time (HH:MM): ");
            if (answer.matches("\\d{2}:\\d{2}")) {
                try {
                    String[] time = answer.split(":");
                    int hours = Integer.parseInt(time[0]);
                    int minutes = Integer.parseInt(time[1]);
                    if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {
                        validTime = true;
                    } else {
                        System.out.println(errorColor + "Invalid time. Please enter a valid time (HH:MM)." + resetColor);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(errorColor + "Invalid time. Please enter a valid time (HH:MM)." + resetColor);
                }
            } else {
                System.out.println(errorColor + "Invalid format. Please enter the time in the format HH:MM." + resetColor);
            }
        }

        return answer;
    }

    public String trueFalseAnswer(String answer, String errorColor, String resetColor) {
        boolean validAnswer = false;

        while (!validAnswer) {
            answer = Console.readLine("Enter True or False: ");
            if (answer.equalsIgnoreCase("True") || answer.equalsIgnoreCase("False")) {
                validAnswer = true;
            } else {
                System.out.println(errorColor + "Invalid input. Please enter either True or False." + resetColor);
            }
        }

        return answer;
    }
}
