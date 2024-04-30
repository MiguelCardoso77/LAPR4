package core.domain.jobOpening;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JobReferenceTest {

    @Test
    void testJobReferenceCreation() {
        JobReference jobReference = new JobReference("CompanyXYZ");
        assertNotNull(jobReference);
    }

    @Test
    void testJobReferenceInequality() {
        JobReference jobReference1 = new JobReference("CompanyXYZ");
        JobReference jobReference2 = new JobReference("CompanyABC");
        assertNotEquals(jobReference1, jobReference2);
    }

}
