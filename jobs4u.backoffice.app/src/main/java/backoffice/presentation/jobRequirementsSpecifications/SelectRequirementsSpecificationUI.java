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

import java.util.ArrayList;
import java.util.List;

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

        showJobRequirementsSpecification();
        JobRequirementsSpecification jobRequirementsSpecification = selectJobRequirementsSpecification();



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
                System.out.printf("%-6s%-30s-30s%n", cont, jobOpening.identity(), jobOpening.description());
                cont++;
            }
        }
    }

    private void showJobRequirementsSpecification(){
        final Iterable<JobRequirementsSpecification> iterable = listJobRequirementsSpecification.allJobRequirementsSpecification();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job requirements specifications");
        }else{
            int cont1 = 1;
            System.out.println("List of registered Job Requirements Specifications: \n");
            for (JobRequirementsSpecification jobRequirementsSpecification : iterable) {
                System.out.printf("%-6s%-30s%n", cont1, jobRequirementsSpecification.identity());
                cont1++;
            }
        }
    }

    private JobRequirementsSpecification selectJobRequirementsSpecification(){
        final List<JobRequirementsSpecification> list = new ArrayList<>();
        for(JobRequirementsSpecification jobRequirementsSpecification : listJobRequirementsSpecification.allJobRequirementsSpecification()){
            list.add(jobRequirementsSpecification);
        }

        JobRequirementsSpecification jobRequirementsSpecification = null;
        final int option = Console.readInteger("Enter the number of the job requirements specification");
        if(option == 0){
            System.out.println("No job requirements specifications selected");
        }else{
            try{
                jobRequirementsSpecification = this.listJobRequirementsSpecification.findJobRequirementSpecification(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobRequirementsSpecification;
    }

    private JobOpening selectJobOpening(){
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : listJobOpeningController.allJobOpening()) {
            list.add(jobOpening);
        }
        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if(option == 0){
            System.out.println("No job opening selected");
        }else{
            try {
                jobOpening = this.listJobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            }catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }

    @Override
    public String headline() {
        return "Select Requirements Specifications";
    }
}