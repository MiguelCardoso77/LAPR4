package core.application.controllers;

import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.repositories.ProcessRepository;

import java.util.ArrayList;
import java.util.List;

public class ChangeProcessStatusController {

    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();
    final ListProcessJobOpeningController listProcessJobOpeningController = new ListProcessJobOpeningController();
    Iterable<JobOpening> iterable = jobOpeningController.allJobOpening();
    List<Process> processList = new ArrayList<>();

    public Iterable<JobOpening> showJobOpenings() {
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int cont = 1;
            System.out.println("List of registered Job Openings: \n");
            for (JobOpening jobOpening : iterable) {
                System.out.printf("%-6s%-30s%-30s%-30s%n", cont, jobOpening.jobReference(), jobOpening.titleOrFunction(), jobOpening.company());
                cont++;
            }
        }
        return iterable;
    }


    public Iterable<Process> showProcessOfJobOpening(JobOpening jobOpening) {
        processList = (List<Process>) listProcessJobOpeningController.allProcessJobOpening(jobOpening);
        if (processList.isEmpty()) {
            System.out.println("There are no Processes for this jobOpening");
        } else {
            int cont = 1;
            System.out.println("Current Process: ");
            System.out.printf("%-30s%-30s%-40s%-30s%n", "Process ID ", "Process State", "Process Date",  "Process Status" );

            for (Process process : processList) {
                System.out.printf("%-30s%-30s%-40s%-30s%n",cont, process.processState(), process.processDate().getTime(), process.processStatus());
                cont++;
            }
        }
        return processList;
    }

    public List<ProcessStatus> listProcessStatus() {
        return List.of(ProcessStatus.values());
    }

    public Process changeProcessStatus(ProcessStatus processStatus, Process process) {
        process.changeProcessStatus(processStatus);
        return processRepository.save(process);
    }
}
