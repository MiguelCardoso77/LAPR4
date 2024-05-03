package core.domain.jobOpening;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobReferenceTest {

    @Test
    void testJobReferenceCreation() {
        JobReference jobReference = new JobReference("CompanyXYZ", true);
        assertNotNull(jobReference);
    }

    @Test
    void testJobReferenceInequality() {
        JobReference jobReference1 = new JobReference("CompanyXYZ", true);
        JobReference jobReference2 = new JobReference("CompanyABC", true);
        assertNotEquals(jobReference1, jobReference2);
    }

    @Test
    void testJobReferenceEquality() {
        JobReference jobReference1 = new JobReference("CompanyXYZ", false);
        JobReference jobReference2 = new JobReference("CompanyXYZ", false);
        assertEquals(jobReference1, jobReference2);
    }

    @Test
    void testJobReferenceHashCode() {
        JobReference jobReference1 = new JobReference("CompanyXYZ", false);
        JobReference jobReference2 = new JobReference("CompanyXYZ", false);
        assertEquals(jobReference1.hashCode(), jobReference2.hashCode());
    }

    @Test
    void testJobReferenceCompareTo() {
        JobReference jobReference1 = new JobReference("CompanyXYZ", false);
        JobReference jobReference2 = new JobReference("CompanyABC", false);
        assertTrue(jobReference1.compareTo(jobReference2) > 0);
    }

}
