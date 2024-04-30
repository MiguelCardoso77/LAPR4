package core.domain.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class SubmissionDateTest {

    private SubmissionDate submissionDate;

    @BeforeEach
    void setUp() {
        // Create a sample submission date (e.g., May 1, 2024)
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MAY, 1);
        submissionDate = SubmissionDate.valueOf(calendar);
    }

    @Test
    void testEquals() {
        // Create another submission date with the same value
        Calendar sameDate = Calendar.getInstance();
        sameDate.set(2024, Calendar.MAY, 1);
        SubmissionDate sameSubmissionDate = SubmissionDate.valueOf(sameDate);

        assertEquals(submissionDate, sameSubmissionDate);
    }

    @Test
    void testNotEquals() {
        // Create a different submission date
        Calendar differentDate = Calendar.getInstance();
        differentDate.set(2024, Calendar.APRIL, 30);
        SubmissionDate differentSubmissionDate = SubmissionDate.valueOf(differentDate);

        assertNotEquals(submissionDate, differentSubmissionDate);
    }

    @Test
    void testCompareTo() {
        // Create a submission date for May 2, 2024
        Calendar laterDate = Calendar.getInstance();
        laterDate.set(2024, Calendar.MAY, 2);
        SubmissionDate laterSubmissionDate = SubmissionDate.valueOf(laterDate);

        assertTrue(submissionDate.compareTo(laterSubmissionDate) < 0);
    }



    @Test
    void testHashCode() {
        // Ensure hash code consistency
        assertEquals(submissionDate.hashCode(), submissionDate.hashCode());
    }
}
