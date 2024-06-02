package core.domain.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessBuilderTest {

    private ProcessBuilder processBuilder;
    private ProcessState processState;
    private ProcessStatus processStatus;

    @BeforeEach
    void setUp() {
        processState = ProcessState.APPLICATION;
        processStatus = ProcessStatus.OPEN;

        processBuilder = new ProcessBuilder();
    }

    @Test
    void testWithAll() {
        processBuilder.withAll(processState, processStatus);
        Process process = processBuilder.build();

        assertEquals(processState, process.processState());
        assertEquals(processStatus, process.processStatus());
    }

    @Test
    void testBuild() {
        processBuilder.withAll(processState, processStatus);
        Process process = processBuilder.build();

        assertNotNull(process);
    }

}