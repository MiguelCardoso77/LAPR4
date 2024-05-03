package core.application.controllers;

import core.domain.interview.QuestionType;
import core.domain.jobRequirementsSpecification.AcademicDegree;
import core.domain.jobRequirementsSpecification.ProgrammingLanguages;
import eapli.framework.io.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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



}
