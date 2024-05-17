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
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    final ChangeProcessStatusController changeProcessStatusController = new ChangeProcessStatusController();
    final ChangeProcessStateController changeProcessStateController = new ChangeProcessStateController();
    final ListProcessJobOpeningController listProcessJobOpeningController = new ListProcessJobOpeningController();
    Iterable<JobOpening> jobOpenings = new ArrayList<>();
    Iterable<Process> processes = new ArrayList<>();

    /**
     * Displays the UI for changing the process status.
     *
     * @return false to indicate the UI should not loop.
     */
    @Override
    protected boolean doShow() {

        showJobOpenings();
        JobOpening jobOpening = selectJobOpening();
        showProcessOfJobOpening(jobOpening);
        Process process = selectProcess();

        if (process != null) {
            selectStatus(process);
        }
        return false;
    }

    /**
     * Displays the list of job openings.
     */
    private void showJobOpenings() {
        jobOpenings = changeProcessStatusController.showJobOpenings();
    }

    /**
     * Prompts the user to select a job opening.
     *
     * @return the selected job opening.
     */
    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Displays the processes of the selected job opening.
     *
     * @param jobOpening the selected job opening.
     */
    private void showProcessOfJobOpening(JobOpening jobOpening) {
        processes = changeProcessStatusController.showProcessOfJobOpening(jobOpening);
    }

    /**
     * Prompts the user to select a process.
     *
     * @return the selected process.
     */
    private Process selectProcess() {
        final List<Process> list = new ArrayList<>();
        if (processes.iterator().hasNext()) {
            for (Process process : processes) {
                list.add(process);
            }

            Process process = null;
            final int option = Console.readInteger("Enter the number of the process");
            if (option == 0) {
                System.out.println("No processes selected");
            } else {
                try {
                    process = listProcessJobOpeningController.findProcessById(list.get(option - 1).identity());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
                }
            }

            return process;
        }
        return null;
    }

    /**
     * Prompts the user to select a status change for the process.
     *
     * @param process the selected process.
     */
    private void selectStatus(Process process) {

        ProcessState state = process.processState();
        String name = state.name();
        final int optionMove = Console.readInteger("Do you want to move: \n 1 - Back \n 2 - Forward \n" + "Choose an option: ");

        if (optionMove == 1){
            switch (name) {
                case "APPLICATION":
                    changeProcessStatus(ProcessStatus.OPEN, process);

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
            selectStatus(process);
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
