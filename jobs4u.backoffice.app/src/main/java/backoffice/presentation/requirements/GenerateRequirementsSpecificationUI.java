package backoffice.presentation.requirements;

import core.application.controllers.GenerateRequirementsSpecificationController;
import core.domain.interview.QuestionType;
import core.domain.jobRequirementsSpecification.AcademicDegree;
import core.domain.jobRequirementsSpecification.ProgrammingLanguages;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateRequirementsSpecificationUI extends AbstractUI {
    private final GenerateRequirementsSpecificationController theController = new GenerateRequirementsSpecificationController();

    @Override
    protected boolean doShow() {

        List<String> requirementsForFile = new ArrayList<>();
        String title = "# Requirements:";
        requirementsForFile.add(title);



        theController.displayAcademicDegree();
        int degree = Console.readInteger("Select the degree: ");
        String degreeString = "-> Academic Degree: " + AcademicDegree.values()[degree - 1];

        String progLangString = readAndProcessProgrammingLanguages();




        int experience = Console.readInteger("Write your experience in years");
        String experienceString = "-> Years of Experience: " + experience;

        requirementsForFile.add(degreeString);
        requirementsForFile.add(progLangString);
        requirementsForFile.add(experienceString);

        String fileName = Console.readLine("Enter the name of the file: ");
        theController.writeListToFile(requirementsForFile, "jobs4u.core/src/main/resources/requirements/" + fileName + ".txt");











        return true;
    }

    public String readAndProcessProgrammingLanguages() {
        List<String> selectedLanguages = new ArrayList<>();
        while (true) {
            theController.displayProgrammingLanguages();
            int progLang = Console.readInteger("Select one or more programming languages (Enter -1 to finish): ");
            if (progLang == -1) {
                break; // Exit the loop if -1 is entered
            }
            if (progLang < 1 || progLang > theController.getProgrammingLanguages().size()) {
                System.out.println("Invalid choice. Please enter a valid option.");
                continue; // Restart the loop if an invalid option is entered
            }
            selectedLanguages.add(ProgrammingLanguages.values()[progLang - 1].toString());
        }

        // Construir a string com as linguagens selecionadas
        StringBuilder progLangString = new StringBuilder();
        progLangString.append("-> Programming Languages: ");
        for (String lang : selectedLanguages) {
            progLangString.append(lang).append(", ");
        }
        if (!selectedLanguages.isEmpty()) {
            // Remover a vírgula extra no final
            progLangString.setLength(progLangString.length() - 2);
        }

        return progLangString.toString();
    }

    @Override
    public String headline() {
        return "Generate Template File";
    }

}
