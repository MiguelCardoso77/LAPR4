package core.domain.process;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProcessStateTest {

    @Test
    void testEnumValues() {
        // Test that all enum values are defined correctly
        assertEquals(ProcessState.APPLICATION, ProcessState.valueOf("APPLICATION"));
        assertEquals(ProcessState.SCREENING, ProcessState.valueOf("SCREENING"));
        assertEquals(ProcessState.INTERVIEWS, ProcessState.valueOf("INTERVIEWS"));
        assertEquals(ProcessState.ANALYSIS, ProcessState.valueOf("ANALYSIS"));
        assertEquals(ProcessState.RESULT, ProcessState.valueOf("RESULT"));
    }

    @Test
    void testDistinctValues() {
        // Test that enum values are distinct
        assertNotEquals(ProcessState.APPLICATION, ProcessState.SCREENING);
        assertNotEquals(ProcessState.APPLICATION, ProcessState.INTERVIEWS);
        assertNotEquals(ProcessState.APPLICATION, ProcessState.ANALYSIS);
        assertNotEquals(ProcessState.APPLICATION, ProcessState.RESULT);
        assertNotEquals(ProcessState.SCREENING, ProcessState.INTERVIEWS);
        assertNotEquals(ProcessState.SCREENING, ProcessState.ANALYSIS);
        assertNotEquals(ProcessState.SCREENING, ProcessState.RESULT);
        assertNotEquals(ProcessState.INTERVIEWS, ProcessState.ANALYSIS);
        assertNotEquals(ProcessState.INTERVIEWS, ProcessState.RESULT);
        assertNotEquals(ProcessState.ANALYSIS, ProcessState.RESULT);
    }
}
