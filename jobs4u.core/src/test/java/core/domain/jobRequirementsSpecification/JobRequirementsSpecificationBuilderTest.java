package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JobRequirementsSpecificationBuilderTest {

    @Test
    public void testWithAll() {
        Integer idRequirements = 1;
        String academicDegree = "Bachelor's Degree";
        int experience = 2;
        String knowledge = "Java programming";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withAll(idRequirements, academicDegree, experience, knowledge).build();

        assertEquals(academicDegree, requirements.academicDegree());
        assertEquals(experience, requirements.experience());
        assertEquals(knowledge, requirements.knowledge());
    }

    @Test
    public void testWithoutId() {
        String academicDegree = "Bachelor's Degree";
        int experience = 2;
        String knowledge = "Java programming";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withoutId(academicDegree, experience, knowledge).build();

        assertNull(requirements.identity());
        assertEquals(academicDegree, requirements.academicDegree());
        assertEquals(experience, requirements.experience());
        assertEquals(knowledge, requirements.knowledge());
    }

    @Test
    public void testBuildMissingAttributes() {
        String academicDegree = "Bachelor's Degree";
        int experience = 2;

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            builder.withoutId(academicDegree, experience, null).build();
        });
    }
}
