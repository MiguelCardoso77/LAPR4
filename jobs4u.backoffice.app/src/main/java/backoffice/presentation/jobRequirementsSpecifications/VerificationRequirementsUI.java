package backoffice.presentation.jobRequirementsSpecifications;


import backoffice.presentation.application.ListJobOpeningApplicationsUI;
import core.application.controllers.ListJobOpeningApplicationsController;
import core.application.controllers.ListJobOpeningController;
import core.application.controllers.SearchJobRequirementsFileController;
import core.application.controllers.VerificationRequirementsController;
import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerificationRequirementsUI extends AbstractUI {


    private final ListJobOpeningController theController = new ListJobOpeningController();
    private final ListJobOpeningApplicationsController theController1 = new ListJobOpeningApplicationsController();
    private final VerificationRequirementsController theController2 = new VerificationRequirementsController();
    private final SearchJobRequirementsFileController theController3 = new SearchJobRequirementsFileController();


    private static final Logger LOGGER = LoggerFactory.getLogger(ListJobOpeningApplicationsUI.class);


    @Override
    protected boolean doShow() {
        final List<JobOpening> list = new ArrayList<>();
        final List<Application> list1 = new ArrayList<>();
        final Iterable<JobOpening> iterable = elements();
        JobOpening jobOpeningApplication = null;
        Application applicationToVerify = null;
        int count = 0;
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no job openings ");
        } else {
            int cont = 1;
            System.out.println("Select a Job Opening: \n");
            System.out.printf("%-30s%-30s%n", "Title or Function:", "Job Reference:");
            for (JobOpening jobOpening : iterable) {
                list.add(jobOpening);
                System.out.printf("%-6s%-30s%-30s%n",cont, jobOpening.titleOrFunction(), jobOpening.jobReference());
                cont++;
            }
            final int option = Console.readInteger("Enter the number of job opening");
            if (option == 0) {
                System.out.println("No job opening selected");
            } else {
                try {
                    jobOpeningApplication = theController1.findJobOpening(list.get(option - 1).jobReference());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                }
            }
        }
        if (jobOpeningApplication != null) {

            final Iterable<Application> iterable1 = theController1.allApplicationsOfJobOpening(jobOpeningApplication.jobReference());
            if (!iterable1.iterator().hasNext()) {
                System.out.println("There are no applications for this job opening ");
            } else {
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status",  "Job Reference" , "Candidate");
                for (Application application : iterable1) {
                    list1.add(application);
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", application.identity(), application.rank(), "Submitted", application.jobReference().jobReference(), application.candidate().user().identity());
                }
                final int option = Console.readInteger("Enter the number of application");
                if (option == 0) {
                    System.out.println("No application selected");
                } else {
                    try {
                        applicationToVerify = theController2.findApplicationById(list1.get(option - 1));
                    } catch (IntegrityViolationException | ConcurrencyException ex) {
                        LOGGER.error("Error performing the operation", ex);
                        System.out.println(
                                "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
                    }
                }
            }

        }



        if(applicationToVerify != null) {

            /*
            String file = jobOpeningApplication.jobRequirementsSpecification().toString();

            String degree = jobOpeningApplication.jobRequirementsSpecification().academicDegree();

            boolean degreeCorrect = theController3.verifyWord(file, degree);

            if (degreeCorrect) {
                count++;
            }

            String progLang = jobOpeningApplication.jobRequirementsSpecification().knowledge();

            boolean progLangCorrect = theController3.verifyWord(file, progLang);

            if (progLangCorrect) {
                count++;
            }

            int years;

            try {
                years = theController3.processarFicheiro(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(jobOpeningApplication.jobRequirementsSpecification().experience() >= years){
                count++;
            }

            if(count == 3){
                System.out.println("Application accepted");
            }else {
                System.out.println("Application declined");

            }

             */


        }

        return true;
    }

    /**
     * Retrieves all job openings.
     *
     * @return Iterable of all job openings.
     */
    protected Iterable<JobOpening> elements() {
        return theController.allJobOpening();
    }

    @Override
    public String headline() {
        return "";
    }


}

