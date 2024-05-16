package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SelectJobOpeningController {
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectJobOpeningController.class);

    Iterable<JobOpening> jobOpenings = jobOpeningController.allJobOpening();

    public JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        if (jobOpenings.iterator().hasNext()) {
            for (JobOpening jobOpening : jobOpenings) {
                list.add(jobOpening);
            }

            JobOpening jobOpening = null;
            final int option = Console.readInteger("Enter the number of the job opening");
            if (option == 0) {
                System.out.println("No job opening selected");
            } else {
                try {
                    jobOpening = jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
                }
            }

            return jobOpening;
        }
        return null;
    }




}
