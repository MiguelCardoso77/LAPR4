package core.application.controllers;

import core.domain.jobRequirementsSpecification.AcademicDegree;
import core.domain.jobRequirementsSpecification.ProgrammingLanguages;
import eapli.framework.io.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsSpecificationController {

    public List<String> getAcademicDegree() {
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
    }

}
