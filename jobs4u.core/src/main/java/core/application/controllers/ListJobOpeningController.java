package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.process.ProcessState;
import core.services.JobOpeningService;

public class ListJobOpeningController {
    private final JobOpeningService jobserv = new JobOpeningService();

    public Iterable<JobOpening> showJobOpenings() {
        Iterable<JobOpening> iterable = allJobOpenings();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings: ");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
        }
        return iterable;
    }

    public Iterable<JobOpening> showJobOpeningsAnalysis(){
        Iterable<JobOpening> iterable = allJobOpenings();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings in Analysis phase: ");
            for (JobOpening jobOpening : iterable) {
                if (jobOpening.process().processState().equals(ProcessState.ANALYSIS)) {
                    System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                    cont++;
                }
            }
        }
        return iterable;
    }

    public Iterable<JobOpening> allJobOpenings() {
        return jobserv.allJobOpenings();
    }

    public JobOpening findJobOpeningByJobReference(JobReference jobReference){
        return jobserv.findJobOpening(jobReference);
    }

}
