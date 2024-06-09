package core.application.controllers;

import core.domain.interviewModel.InterviewModel;
import core.services.InterviewModelService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller for generating answers template in the Jobs4U system.
 * This class provides methods to read a file, write a list to a file, find all interview models,
 * find an interview model by ID, and process lines.
 * It uses the InterviewModelService from the core services.
 *
 * @author Miguel Cardoso
 */
public class GenerateAnswersTemplateController {
    private final InterviewModelService interviewModelService = new InterviewModelService();
    /**
     * Reads a file and returns its lines.
     *
     * @param filePath the path of the file to read
     * @return a list of lines in the file
     */
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
    /**
     * Writes a list of strings to a file.
     *
     * @param questionForFile the list of strings to write to the file
     * @param filePath the path of the file to write to
     * @return true if the operation was successful, false otherwise
     */
    public boolean writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Retrieves all interview models.
     *
     * @return an iterable collection of all interview models
     */
    public Iterable<InterviewModel> findAllInterviewModels() {
        return interviewModelService.allInterviewModels();
    }
    /**
     * Finds an interview model by its ID.
     *
     * @param interviewModelID the ID of the interview model to find
     * @return the interview model with the specified ID
     */
    public InterviewModel findInterviewModelByID(int interviewModelID) {
        return interviewModelService.findById(interviewModelID);
    }
    /**
     * Processes lines by removing any characters after the second ">" character in each line.
     *
     * @param lines the lines to process
     * @return a list of processed lines
     */
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
