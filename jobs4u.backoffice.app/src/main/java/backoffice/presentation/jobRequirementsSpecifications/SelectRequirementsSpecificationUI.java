package backoffice.presentation.jobRequirementsSpecifications;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import console.presentation.utils.ConsoleColors;
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
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        System.out.println("\nAvailable Requirements Specifications: ");
        showJobRequirements();

        JobRequirementsSpecification jobRequirementsSpecification = selectRequirement();

        JobOpening updatedJobOpening = listJobRequirementsSpecification.updateJobOpening(jobOpening.jobReference(), jobRequirementsSpecification);

        if (updatedJobOpening.jobRequirementsSpecification().identity() != null) {
            System.out.println(ConsoleColors.GREEN + "\nRequirements specifications selected!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "\nFailed to select the requirements specifications." + ConsoleColors.RESET);
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

    private void showJobRequirements(){
        final Iterable<JobRequirementsSpecification> iterable = listJobRequirementsSpecification.allJobRequirementsSpecification();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job requirements registered");
        }else{
            System.out.println("List of registered job requirements");
            for (JobRequirementsSpecification jobRequirementsSpecification : iterable) {
                System.out.printf("%-6s%-30s%n", jobRequirementsSpecification.identity(), jobRequirementsSpecification.jobRequirementsPath());
            }
        }
    }

    private JobRequirementsSpecification selectRequirement(){
        final List<JobRequirementsSpecification> list = new ArrayList<>();
        for (JobRequirementsSpecification requirement : listJobRequirementsSpecification.allJobRequirementsSpecification()) {
            list.add(requirement);
        }
        JobRequirementsSpecification jobRequirementsSpecification = null;
        final int option = Console.readInteger("Enter the number of the file you want to select (0 to cancel): ");
        if (option == 0) {
            System.out.println("No job requirement specification selected");
            System.exit(0);
        } else {
            try {
                jobRequirementsSpecification = this.listJobRequirementsSpecification.findJobRequirementSpecification(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex ){
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobRequirementsSpecification;
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
            System.exit(0);
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