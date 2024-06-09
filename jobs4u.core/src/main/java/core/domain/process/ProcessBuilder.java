package core.domain.process;

import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * Builder class for creating instances of {@link Process}.
 */
public class ProcessBuilder implements DomainFactory<Process> {
    private static final Logger LOGGER = LogManager.getLogger(JobOpening.class);
    private ProcessState processState;
    private Calendar processDate;
    private ProcessStatus processStatus;

    /**
     * Sets all attributes of the builder.
     *
     * @param processState  the state of the process
     *
     * @return this builder instance
     */
    public ProcessBuilder withAll(ProcessState processState, ProcessStatus processStatus){
        this.processState = processState;
        this.processDate = Calendar.getInstance();
        this.processStatus = processStatus;
        return this;
    }

    /**
     * Builds a new instance of {@link Process}.
     *
     * @return the built {@link Process} instance, or null if mandatory information is missing
     */
    @Override
    public Process build() {
        Process process;

        if (processState == null || processDate == null) {
            LOGGER.error("Missing mandatory information to build a Process");
            return null;
        } else {
            LOGGER.debug("Creating a new process... ");
            process = new Process(processState, processDate, processStatus);
        }
        return process;
    }
}
