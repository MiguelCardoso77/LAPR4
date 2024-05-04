package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JobRequirementsSpecificationTest {

    @Test
    public void testConstructor() {
        String academicDegree = "Bachelor's degree";
        int experience = 3;
        String knowledge = "Java programming";

        JobRequirementsSpecification jobReqSpec = new JobRequirementsSpecification(academicDegree, experience, knowledge);

        assertEquals(academicDegree, jobReqSpec.academicDegree());
        assertEquals(experience, jobReqSpec.experience());
        assertEquals(knowledge, jobReqSpec.knowledge());
    }

    @Test
    public void testEqualsAndHashCode() {
        JobRequirementsSpecification jobReqSpec1 = new JobRequirementsSpecification("Bachelor's degree", 3, "Java programming");
        JobRequirementsSpecification jobReqSpec2 = new JobRequirementsSpecification("Bachelor's degree", 3, "Java programming");
        JobRequirementsSpecification jobReqSpec3 = new JobRequirementsSpecification("Master's degree", 5, "Python programming");

        assertEquals(jobReqSpec1, jobReqSpec2);
        assertEquals(jobReqSpec1.hashCode(), jobReqSpec2.hashCode());
        assertNotEquals(jobReqSpec1, jobReqSpec3);
        assertNotEquals(jobReqSpec1.hashCode(), jobReqSpec3.hashCode());
    }

    @Test
    public void testGetters() {
        JobRequirementsSpecification jobReqSpec = new JobRequirementsSpecification("Bachelor's degree", 3, "Java programming");

        assertEquals("Bachelor's degree", jobReqSpec.academicDegree());
        assertEquals(3, jobReqSpec.experience());
        assertEquals("Java programming", jobReqSpec.knowledge());
    }
}