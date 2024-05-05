package core.domain.process;

import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class ProcessBuilder implements DomainFactory<Process> {
    private static final Logger LOGGER = LogManager.getLogger(JobOpening.class);

    private ProcessState processState;
    private Calendar processDate;
    private JobOpening jobReference;

    public ProcessBuilder withAll(ProcessState processState, JobOpening jobReference){
        this.processState = processState;
        this.processDate = Calendar.getInstance();
        this.jobReference = jobReference;
        return this;
    }


    @Override
    public Process build() {
        Process process;

        if (processState == null || processDate == null || jobReference == null ) {
            LOGGER.error("Missing mandatory information to build a Process");
            return null;
        } else {
            LOGGER.debug("Building Process with processState{}, processDate{}, jobReference {}", processState, processDate, jobReference );
            process = new Process(processState, processDate, jobReference);
        }
        return process;
    }
}
