package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AcademicDegreeTest {

    @Test
    public void testToString() {
        assertEquals("None", AcademicDegree.NONE.toString());
        assertEquals("Bachelor", AcademicDegree.BACHELOR.toString());
        assertEquals("Master", AcademicDegree.MASTER.toString());
        assertEquals("Doctorate", AcademicDegree.DOCTORATE.toString());
    }
}
