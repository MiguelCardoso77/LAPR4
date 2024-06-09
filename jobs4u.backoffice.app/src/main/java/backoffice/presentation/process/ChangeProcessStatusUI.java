package backoffice.presentation.process;

import core.application.controllers.*;
import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI class for changing the status of a process in the job opening context.
 * This UI allows users to navigate through different states of the job opening process
 * and change its status accordingly.
 * It provides options to move forward or backward in the process flow.
 * Users can select a job opening and choose to move the process status forward or backward based on the current state.
 * The UI displays the current state and status of the selected job opening process.
 * Upon changing the status or state of the process, the UI provides feedback on the successful update.
 * The headline for this UI is "change status of a phase of a job opening".
 *
 * @author Diana Neves
 */
public class ChangeProcessStatusUI extends AbstractUI {
    final ChangeProcessStatusController changeProcessStatusController = new ChangeProcessStatusController();

    /**
     * Displays the UI for changing the process status.
     *
     * @return false to indicate the UI should not loop.
     */
    @Override
    protected boolean doShow() {

        System.out.println("\nAvailable Job Openings: ");
        JobOpening jobOpening = changeProcessStatusController.selectJobOpening();
        selectStatus(jobOpening);

        return false;
    }

    /**
     * Allows the user to select the status of the process based on the current state of the job opening.
     *
     * @param jobOpening the selected job opening.
     */
    private void selectStatus(JobOpening jobOpening) {

        Process process = jobOpening.process();
        ProcessState state = process.processState();
        ProcessStatus status = process.processStatus();
        String nameState = state.name();
        String nameStatus = status.name();
        System.out.printf("%-30s%-30s%-30S%n", "Job Opening", "State", "Status");
        System.out.printf("%-30s%-30s%-30S%n", jobOpening.identity(), nameState, nameStatus);
        final int optionMove = Console.readInteger("\nDo you want to move: \n 1 - Back \n 2 - Forward \n" + "Choose an option: ");

        if (optionMove == 1) {
            switch (nameState) {
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

        } else if (optionMove == 2) {
            switch (nameState) {
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
                    System.out.println("You cannot move forward because this is the last state of the process");
                    System.out.println("Status of the process: " + process.processStatus().toString());

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
     * @param process       the process to change.
     */
    public void changeProcessStatus(ProcessStatus processStatus, Process process) {
        Process newProcess = changeProcessStatusController.changeProcessStatus(processStatus, process);
        System.out.println("Success: Status was updated to " + newProcess.processStatus() + " for the process " + newProcess.identity());
    }

    /**
     * Changes the state of the process.
     *
     * @param processState the new state.
     * @param process      the process to change.
     */
    public void changeProcessState(ProcessState processState, Process process) {
        Process newProcess = changeProcessStatusController.changeProcessState(processState, process);
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
