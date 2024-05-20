package backoffice.presentation.requirements;

import core.application.controllers.GenerateRequirementsSpecificationController;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobRequirementsSpecification.AcademicDegree;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsSpecificationUI extends AbstractUI {
    private final GenerateRequirementsSpecificationController theController = new GenerateRequirementsSpecificationController();

    @Override
    protected boolean doShow() {

        List<JobRequirementsSpecification> allJobRequirementsSpecification = theController.findAllJobRequirementsAssigned();

        System.out.println("Job Requirements: ");
        for (JobRequirementsSpecification jobRequirementsSpecification : allJobRequirementsSpecification) {
            System.out.println("ID: " + jobRequirementsSpecification.identity() + " - " + jobRequirementsSpecification.jobRequirementsPath());
        }

        int idRequirements = Console.readInteger("\nChoose a Job Requirement: ");
        JobRequirementsSpecification jobRequirementsSpecification = theController.getJobRequirementByID(idRequirements);

        List<String> model = theController.readFile(jobRequirementsSpecification.jobRequirementsPath());
        List<String> templateLines = theController.processLines(model);

        String fileName = "jobRequirementsSpecification-id-" + idRequirements + "-answerTemplate";
        theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/requirements/" + fileName + ".txt");

        return true;


        /*List<String> requirementsForFile = new ArrayList<>();
        String title = "# Requirements:";
        requirementsForFile.add(title);



        theController.displayAcademicDegree();
        int degree = Console.readInteger("Select the degree: ");
        String degreeString = "-> Academic Degree: " + AcademicDegree.values()[degree - 1];

        String progLangString = theController.readAndProcessProgrammingLanguages();




        int experience = Console.readInteger("Write your experience in years");
        String experienceString = "-> Years of Experience: " + experience;

        requirementsForFile.add(degreeString);
        requirementsForFile.add(progLangString);
        requirementsForFile.add(experienceString);

        String fileName = Console.readLine("Enter the name of the file: ");
        theController.writeListToFile(requirementsForFile, "jobs4u.core/src/main/resources/requirements/" + fileName + ".txt");


*/




    }



    @Override
    public String headline() {
        return "Generate Template File";
    }

}
