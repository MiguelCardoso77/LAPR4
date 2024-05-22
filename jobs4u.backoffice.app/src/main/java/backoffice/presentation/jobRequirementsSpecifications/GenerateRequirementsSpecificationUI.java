package backoffice.presentation.jobRequirementsSpecifications;

import backoffice.presentation.jobRequirementsSpecifications.SelectRequirementsSpecificationUI;
import core.application.controllers.GenerateAnswersTemplateController;
import core.application.controllers.GenerateRequirementsSpecificationController;
import core.application.controllers.SelectJobOpeningController;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.AcademicDegree;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsSpecificationUI extends AbstractUI {
    private final GenerateRequirementsSpecificationController theController = new GenerateRequirementsSpecificationController();
    private final SelectJobOpeningController theController1 = new SelectJobOpeningController();

    @Override
    protected boolean doShow() {

        List<JobOpening> allJobOpening = theController.findAllJobOpeningAssigned();


        System.out.println("Job Opening: ");
        for (JobOpening jobOpening : allJobOpening) {
            System.out.println("ID: " + jobOpening.identity() + " - " + jobOpening.jobRequirementsSpecification());
        }



        JobOpening jobopening = theController1.selectJobOpening();

        List<String> model = theController.readFile(jobopening.jobRequirementsSpecification().jobRequirementsPath());
        List<String> templateLines = theController.processLines(model);
        String jobReference = String.valueOf(jobopening.jobReference());
        int id = jobopening.jobRequirementsSpecification().identity();

        String fileName = jobReference + "-requirementsAnswers-" + id  ;
        theController.writeListToFile(templateLines, "jobs4u.core/src/main/resources/jobOpeningRequirements/" + fileName + ".txt");

        return true;

    }



    @Override
    public String headline() {
        return "Generate Template File";
    }

}
