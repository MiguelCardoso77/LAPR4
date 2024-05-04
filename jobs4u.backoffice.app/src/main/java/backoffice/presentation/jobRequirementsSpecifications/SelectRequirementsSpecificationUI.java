package backoffice.presentation.jobRequirementsSpecifications;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import core.application.controllers.ListJobOpeningController;
import core.application.controllers.ListJobRequirementsSpecificationController;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
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

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        String path = selectFile();

        List<String> data;

        try {
            data = listJobRequirementsSpecification.importRequirementsSpecification(Path.of(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JobRequirementsSpecification jobRequirementsSpecification = listJobRequirementsSpecification.extractSpecificationFromFile(data);

        jobOpening.updateJobRequirements(jobRequirementsSpecification);

        if(jobOpening != null){
            System.out.println("Requirements specifications selected!");
            System.out.println(jobOpening);
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

    public String selectFile() {
        Path directory = Paths.get("jobs4u.core/src/main/resources/requirements");
        List<String> files = new ArrayList<>();
        try {
            files = Files.list(directory)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            if (files.isEmpty()) {
                System.out.println("No files found.");
                return null;
            }
            System.out.println("List of files:");
            for (int i = 0; i < files.size(); i++) {
                System.out.println((i + 1) + ". " + files.get(i));
            }

            int option;
            do {
                option = Console.readInteger("Enter the number of the file you want to select (0 to cancel): ");
                if (option < 0 || option > files.size()) {
                    System.out.println("Invalid option. Please select a number between 1 and " + files.size() + ".");
                }
            } while (option < 0 || option > files.size());

            return (option == 0) ? null : files.get(option - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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