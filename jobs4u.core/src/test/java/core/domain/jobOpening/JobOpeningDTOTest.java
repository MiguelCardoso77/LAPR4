package core.domain.jobOpening;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JobOpeningDTOTest {
    private JobOpeningDTO jobOpeningDTO;

    @BeforeEach
    void setUp() {
        jobOpeningDTO = new JobOpeningDTO("ref1", "position1", new Date(), 10);
    }

    @Test
    void testMyJobReference() {
        assertEquals("ref1", jobOpeningDTO.myJobReference());
    }

    @Test
    void testMyPosition() {
        assertEquals("position1", jobOpeningDTO.myPosition());
    }
    @Test
    void testMyNumberOfApplicants() {
        assertEquals(10, jobOpeningDTO.myNumberOfApplicants());
    }
    @Test
    void testConstructor() {
        String expectedJobReference = "ref1";
        String expectedPosition = "position1";
        Date expectedActiveSince = new Date();
        int expectedNumberOfApplicants = 10;

        JobOpeningDTO jobOpeningDTO = new JobOpeningDTO(expectedJobReference, expectedPosition, expectedActiveSince, expectedNumberOfApplicants);

        assertEquals(expectedJobReference, jobOpeningDTO.myJobReference());
        assertEquals(expectedPosition, jobOpeningDTO.myPosition());
        assertEquals(expectedActiveSince, jobOpeningDTO.myActiveSince());
        assertEquals(expectedNumberOfApplicants, jobOpeningDTO.myNumberOfApplicants());
    }

}