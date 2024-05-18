package backoffice.presentation.jobRequirementsSpecifications;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.ListJobOpeningController;
import core.application.controllers.ListJobRequirementsSpecificationController;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobOpeningService;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author 1220812@isep.ipp.pt
 */

public class SelectRequirementsSpecificationUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);

    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final ListJobRequirementsSpecificationController listJobRequirementsSpecification = new ListJobRequirementsSpecificationController();

    private final JobOpeningService service = new JobOpeningService();

    @Override
    protected boolean doShow() {

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        JobRequirementsSpecification jobRequirementsSpecification = showAndSelectRequirement();

        JobOpening updatedJobOpening = service.updateJobRequirements(jobOpening.jobReference(), jobRequirementsSpecification);

        if(updatedJobOpening.jobRequirementsSpecification().identity() != null){
            System.out.println("Requirements specifications selected!");
        }else{
            System.out.println("Failed to select the requirements specifications.");
        }

        return false;
    }

    private void showJobOpenings(){
        final Iterable<JobOpening> iterable = listJobOpeningController.allJobOpening();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job openings");
        }else{
            int cont = 1;
            System.out.println("List of registered Job Openings");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%n", cont, jobOpening.identity());
                cont++;
            }
        }
    }

    private JobRequirementsSpecification showAndSelectRequirement(){
        return listJobRequirementsSpecification.listAndSelectJobRequirementsSpecification();
    }

    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : listJobOpeningController.allJobOpening()) {
            list.add(jobOpening);
        }
        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
        } else {
            try {
                jobOpening = this.listJobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }

    @Override
    public String headline() {
        return "Select Requirements Specifications";
    }
}