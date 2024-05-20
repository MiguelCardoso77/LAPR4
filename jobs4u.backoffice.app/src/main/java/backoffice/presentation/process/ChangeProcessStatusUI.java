package backoffice.presentation.process;

import core.application.controllers.*;
import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * UI class for changing the status of a process in the job opening context.
 *
 * @author Diana Neves
 */
public class ChangeProcessStatusUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeProcessStatusUI.class);
    final ChangeProcessStatusController changeProcessStatusController = new ChangeProcessStatusController();
    final ChangeProcessStateController changeProcessStateController = new ChangeProcessStateController();
    final ListJobOpeningController jobOpeningController = new ListJobOpeningController();

    /**
     * Displays the UI for changing the process status.
     *
     * @return false to indicate the UI should not loop.
     */
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");
        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();

        selectStatus(jobOpening);

        return false;
    }

    /**
     * Displays the list of job openings.
     */
    private void showJobOpenings(){
        final Iterable<JobOpening> iterable = jobOpeningController.allJobOpening();

        if(!iterable.iterator().hasNext()){
            System.out.println("There are no job openings");
        }else{
            int cont = 1;
            System.out.println("List of registered Job Openings");
            for (JobOpening jobOpening : iterable) {
                // TO DO: Mostrar apenas as que estao na fase analysis
                System.out.printf("%-6s%-30s%n", cont, jobOpening.identity());
                cont++;

            }
        }
    }

    /**
     * Method to select one job opening from all the registered in system
     *
     * @return selected job opening
     */

    private JobOpening selectJobOpening() {
        final List<JobOpening> list = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningController.allJobOpening()) {
            list.add(jobOpening);
        }
        JobOpening jobOpening = null;
        final int option = Console.readInteger("Enter the number of the job opening");
        if (option == 0) {
            System.out.println("No job opening selected");
            System.exit(0);
        } else {
            try {
                jobOpening = this.jobOpeningController.findJobOpeningByJobReference(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return jobOpening;
    }

    private void selectStatus(JobOpening jobOpening) {

        Process process = jobOpening.process();

        ProcessState state = process.processState();
        String name = state.name();
        final int optionMove = Console.readInteger("Do you want to move: \n 1 - Back \n 2 - Forward \n" + "Choose an option: ");

        if (optionMove == 1){
            switch (name) {
                case "APPLICATION":
                    System.out.println("You cannot move backwards because this is the first state of the process");
                    System.out.println("Status of the process: " + process.processStatus().toString());
                    break;
                case "SCREENING":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.APPLICATION, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "INTERVIEWS":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.SCREENING, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "ANALYSIS":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.INTERVIEWS, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "RESULT":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.ANALYSIS, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                default:
                    System.out.println("State not valid! \n");
                    break;
            }

        }else if (optionMove == 2){
            switch (name) {
                case "APPLICATION":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.SCREENING, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "SCREENING":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.INTERVIEWS, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "INTERVIEWS":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.ANALYSIS, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "ANALYSIS":
                    changeProcessStatus(ProcessStatus.CLOSE, process);
                    changeProcessState(ProcessState.RESULT, process);
                    changeProcessStatus(ProcessStatus.OPEN, process);

                    break;
                case "RESULT":
                    changeProcessStatus(ProcessStatus.CLOSE, process);

                    break;
                default:
                    System.out.println("State not valid! \n");
                    break;
            }
        } else {
            System.out.println("Option not valid! \n Please try again. \n\n ");
            selectStatus(jobOpening);
        }
    }

    /**
     * Changes the status of the process.
     *
     * @param processStatus the new status.
     * @param process the process to change.
     */
    public void changeProcessStatus(ProcessStatus processStatus, Process process){
        Process newProcess = changeProcessStatusController.changeProcessStatus(processStatus,process);
        System.out.println("Success: Status was updated to " + newProcess.processStatus() + " for the process " + newProcess.identity());
    }

    /**
     * Changes the state of the process.
     *
     * @param processState the new state.
     * @param process the process to change.
     */
    public void changeProcessState(ProcessState processState, Process process){
        Process newProcess = changeProcessStateController.changeProcessStatus(processState,process);
        System.out.println("Success: State was updated to " + newProcess.processState() + " for the process " + newProcess.identity());
    }

    /**
     * Returns the headline for the UI.
     *
     * @return the headline.
     */
    @Override
    public String headline() {
        return "change status of a phase of a job opening";
    }
}
