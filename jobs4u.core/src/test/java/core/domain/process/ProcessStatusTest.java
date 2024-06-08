package core.domain.process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessStatusTest {

    @Test
    void testEnumValues() {
        // Test that all enum values are defined correctly
        assertEquals(ProcessStatus.OPEN, ProcessStatus.valueOf("OPEN"));
        assertEquals(ProcessStatus.CLOSE, ProcessStatus.valueOf("CLOSE"));

    }

    @Test
    void testDistinctValues() {
        // Test that enum values are distinct
        assertNotEquals(ProcessStatus.OPEN, ProcessStatus.CLOSE);
        assertNotEquals(ProcessStatus.CLOSE, ProcessStatus.OPEN);

    }

}