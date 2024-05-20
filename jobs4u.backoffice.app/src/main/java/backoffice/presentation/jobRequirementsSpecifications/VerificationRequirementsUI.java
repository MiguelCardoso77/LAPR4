package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerificationRequirementsUI extends AbstractUI {


    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final VerificationRequirementsController verificationRequirementsController = new VerificationRequirementsController();
    private final SearchJobRequirementsFileController theController3 = new SearchJobRequirementsFileController();
    final Iterable<JobOpening> iterable = elements();
    final List<JobOpening> list = new ArrayList<>();
    final List<Application> list1 = new ArrayList<>();


    @Override
    protected boolean doShow() {
        JobOpening jobOpeningApplication = selectJobOpening();
        Application applicationToVerify = selectApplication(jobOpeningApplication);

        if (applicationToVerify != null) {
            CandidateRequirements candidateRequirements = applicationToVerify.candidateRequirements();
            JobRequirementsSpecification jobOpeningRequirement = jobOpeningApplication.jobRequirementsSpecification();
            System.out.println(jobOpeningRequirement.jobRequirementsPath());

/*
            List<String> typeRequirements = verificationRequirementsController.typeRequirements(jobOpeningRequirement.jobRequirementsPath());

            for(String requirements:  typeRequirements){
                System.out.println(requirements);
            }
*/
            //boolean acceptedApplication = verificationRequirementsController.verifyCandidate(typeRequirements, jobOpeningRequirement);




































            /*String degree = theController3.findDegree(candidateRequirements.candidateRequirements());




            boolean degreeCorrect = theController3.verifyDegree(jobOpeningRequirements.jobRequirementsPath(), degree);


            List<String> progLang = theController3.findProgrammingLanguages(candidateRequirements.candidateRequirements());

            boolean progLangCorrect = theController3.verifyProgLang(jobOpeningRequirements.jobRequirementsPath(), progLang);

            System.out.println(progLangCorrect);

            int years;

            /*try {
                years = theController3.processarFicheiro(directoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

        }
        return true;
    }


    public JobOpening selectJobOpening() {
        JobOpening jobOpeningApplication = null;
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no job openings ");
        } else {
            int cont = 1;
            System.out.println("Select a Job Opening: \n");
            System.out.printf("%-30s%-30s%n", "Title or Function:", "Job Reference:");
            for (JobOpening jobOpening : iterable) {
                list.add(jobOpening);
                System.out.printf("%-6s%-30s%-30s%n", cont, jobOpening.titleOrFunction(), jobOpening.jobReference());
                cont++;
            }
            final int option = Console.readInteger("Enter the number of job opening");
            if (option == 0) {
                System.out.println("No job opening selected");
            } else {
                try {
                    jobOpeningApplication = listJobOpeningApplicationsController.findJobOpening(list.get(option - 1).jobReference());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                }
            }
        }
        return jobOpeningApplication;

    }

    public Application selectApplication(JobOpening jobOpeningApplication) {
        Application applicationToVerify = null;

        if (jobOpeningApplication != null) {
            final Iterable<Application> iterable1 = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobOpeningApplication.jobReference());
            if (!iterable1.iterator().hasNext()) {
                System.out.println("There are no applications for this job opening ");
            } else {
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status", "Job Reference", "Candidate");
                for (Application application : iterable1) {
                    list1.add(application);
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", application.identity(), application.rank(), "Submitted", application.jobReference().jobReference(), application.candidate().user().identity());
                }
                final int option = Console.readInteger("Enter the number of application");
                if (option == 0) {
                    System.out.println("No application selected");
                } else {
                    try {
                        applicationToVerify = verificationRequirementsController.findApplicationById(list1.get(option - 1));
                    } catch (IntegrityViolationException | ConcurrencyException ex) {
                        System.out.println(
                                "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                    }
                }
            }
        }
        return applicationToVerify;
    }


    /**
     * Retrieves all job openings.
     *
     * @return Iterable of all job openings.
     */
    protected Iterable<JobOpening> elements() {
        return listJobOpeningController.allJobOpening();
    }

    @Override
    public String headline() {
        return "";
    }


}
