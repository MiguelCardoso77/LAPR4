package core.application.controllers;

import core.domain.interviewModel.InterviewModel;
import core.services.InterviewModelService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateAnswersTemplateController {
    private final InterviewModelService interviewModelService = new InterviewModelService();

    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public boolean writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Iterable<InterviewModel> findAllInterviewModels() {
        return interviewModelService.allInterviewModels();
    }

    public InterviewModel findInterviewModelByID(int interviewModelID) {
        return interviewModelService.findById(interviewModelID);
    }

    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();

        for (String line : lines) {
            if (!line.isEmpty()) {
                int firstIndex = line.indexOf(">");
                if (firstIndex != -1) {
                    int secondIndex = line.indexOf(">", firstIndex + 1);
                    if (secondIndex != -1) {
                        processedLines.add(line.substring(0, secondIndex + 1));
                    } else {
                        processedLines.add(line);
                    }
                } else {
                    processedLines.add(line);
                }
            }
        }

        return processedLines;
    }
}
