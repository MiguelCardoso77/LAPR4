package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.process.ProcessState;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.ArrayList;
import java.util.List;

public class SelectJobOpeningController {
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();

    public JobOpening selectJobOpening() {
        final List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningController.showJobOpenings();
        return selectorPart(jobOpenings);
    }

    public JobOpening selectJobOpeningAnalysis() {
        final List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningController.showJobOpenings();

        for (JobOpening jobOpening : jobOpenings) {
            if (jobOpening.process().processState() == ProcessState.ANALYSIS) {
                return jobOpening;
            }
        }

        return selectorPart(jobOpenings);
    }

    private JobOpening selectorPart(List<JobOpening> list) {
        JobOpening jobOpening = null;

        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
        } else {
            try {
                jobOpening = jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                System.out.println("There was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return jobOpening;
    }

}
