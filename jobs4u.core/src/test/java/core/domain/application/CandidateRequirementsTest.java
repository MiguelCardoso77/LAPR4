package core.domain.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CandidateRequirementsTest {

    private CandidateRequirements candidateRequirements1;
    private CandidateRequirements candidateRequirements2;
    private CandidateRequirements candidateRequirements3;

    @BeforeEach
    void setUp() {
        List<String> requirements1 = Arrays.asList("Requirement1", "Requirement2");
        List<String> requirements2 = Arrays.asList("Requirement1", "Requirement2");
        List<String> requirements3 = Arrays.asList("Requirement3", "Requirement4");

        candidateRequirements1 = new CandidateRequirements(requirements1);
        candidateRequirements2 = new CandidateRequirements(requirements2);
        candidateRequirements3 = new CandidateRequirements(requirements3);
    }

    @Test
    void testEquals() {
        assertEquals(candidateRequirements1, candidateRequirements2);
        assertNotEquals(candidateRequirements1, candidateRequirements3);
    }

    @Test
    void testHashCode() {
        assertEquals(candidateRequirements1.hashCode(), candidateRequirements2.hashCode());
        assertNotEquals(candidateRequirements1.hashCode(), candidateRequirements3.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(candidateRequirements1.toString(), candidateRequirements2.toString());
        assertNotEquals(candidateRequirements1.toString(), candidateRequirements3.toString());
    }
}