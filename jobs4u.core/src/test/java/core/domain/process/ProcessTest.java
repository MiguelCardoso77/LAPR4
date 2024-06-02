package core.domain.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {
    private Process process;
    private ProcessState processState;
    private Calendar processDate;
    private ProcessStatus processStatus;

    @BeforeEach
    void setUp() {
        processState = ProcessState.APPLICATION;
        processDate = Calendar.getInstance();
        processStatus = ProcessStatus.OPEN;

        process = new Process(processState, processDate, processStatus);
    }

    @Test
    void testIdentity() {
        assertEquals(process.identity(), process.identity());
    }

    @Test
    void testProcessState() {
        assertEquals(processState, process.processState());
    }

    @Test
    void testProcessStatus() {
        assertEquals(processStatus, process.processStatus());
    }

    @Test
    void testProcessDate() {
        assertEquals(processDate, process.processDate());
    }

    @Test
    void testChangeProcessStatus() {
        ProcessStatus newProcessStatus = ProcessStatus.OPEN;
        process.changeProcessStatus(newProcessStatus);

        assertEquals(newProcessStatus, process.processStatus());
    }

    @Test
    void testChangeProcessState() {
        ProcessState newProcessState = ProcessState.APPLICATION;
        process.changeProcessState(newProcessState);

        assertEquals(newProcessState, process.processState());
    }
}