package core.application.controllers;

import core.domain.interview.QuestionType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
}
