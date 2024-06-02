package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;
import core.repositories.JobRequirementsSpecificationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsSpecificationController {

    private final JobRequirementsSpecificationRepository jobRequirementsSpecificationRepository = PersistenceContext.repositories().jobRequirements();

    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();


    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();  // Return empty list instead of null
        }
    }

    public void writeListToFile(List<String> questionForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), questionForFile);
            System.out.println("\nFile created successfully.");
        } catch (IOException e) {
            System.err.println("\nError writing to file.");
            e.printStackTrace();
        }
    }

    public List<JobOpening> findAllJobOpeningAssigned() {
        Iterable<JobOpening> allJobOpening = jobOpeningRepository.allJobOpenings();
        List<JobOpening> filteredJobOpening = new ArrayList<>();

        for (JobOpening jobOpening : allJobOpening) {
            if (jobOpening.jobRequirementsSpecification() != null) {
                filteredJobOpening.add(jobOpening);
            }
        }

        return filteredJobOpening;
    }





    public List<String> processLines(List<String> lines) {
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            int firstIndex = line.indexOf(":");
            if (firstIndex != -1) {
                // Keep everything up to and including the first ":"
                processedLines.add(line.substring(0, firstIndex + 1));
            } else {
                // Add the line as is if no ":" is found
                processedLines.add(line);
            }
        }
        return processedLines;
    }
    /*public List<String> getAcademicDegree() {
        List<String> academicDegree = new ArrayList<>();
        for (AcademicDegree type : AcademicDegree.values()) {
            academicDegree.add(type.toString());
        }
        return academicDegree;
    }

    public void displayAcademicDegree() {
        List<String> academicDegree = getAcademicDegree();
        System.out.println("Choose the academic degree:");
        for (int i = 0; i < academicDegree.size(); i++) {
            System.out.println((i + 1) + ". " + academicDegree.get(i));
        }
    }


    public List<String> getProgrammingLanguages() {
        List<String> programmingLanguages = new ArrayList<>();
        for (ProgrammingLanguages type : ProgrammingLanguages.values()) {
            programmingLanguages.add(type.toString());
        }
        return programmingLanguages;
    }


    public void displayProgrammingLanguages() {
        List<String> programmingLanguages = getProgrammingLanguages();
        System.out.println("Choose the programming languages:");
        for (int i = 0; i < programmingLanguages.size(); i++) {
            System.out.println((i + 1) + ". " + programmingLanguages.get(i));
        }
    }



    public void writeListToFile(List<String> requirementsForFile, String filePath) {
        try {
            Files.write(Paths.get(filePath), requirementsForFile);
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file.");
        }
    }


    public String readAndProcessProgrammingLanguages() {
        List<String> selectedLanguages = new ArrayList<>();
        while (true) {
            displayProgrammingLanguages();
            int progLang = Console.readInteger("Select one or more programming languages (Enter -1 to finish): ");
            if (progLang == -1) {
                break;
            }
            if (progLang < 1 || progLang > getProgrammingLanguages().size()) {
                System.out.println("Invalid choice. Please enter a valid option.");
                continue;
            }
            selectedLanguages.add(ProgrammingLanguages.values()[progLang - 1].toString());
        }

        StringBuilder progLangString = new StringBuilder();
        progLangString.append("-> Programming Languages: ");
        for (String lang : selectedLanguages) {
            progLangString.append(lang).append(", ");
        }
        if (!selectedLanguages.isEmpty()) {
            progLangString.setLength(progLangString.length() - 2);
        }

        return progLangString.toString();
    }*/

}
