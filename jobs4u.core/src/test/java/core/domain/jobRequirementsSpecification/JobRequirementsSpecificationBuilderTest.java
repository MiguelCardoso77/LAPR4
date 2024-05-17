package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JobRequirementsSpecificationBuilderTest {

    @Test
    public void testWithAll() {
        Integer idRequirements = 1;
        String jobRequirementsFilePath = "Path/test";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withAll(idRequirements, jobRequirementsFilePath).build();

        assertEquals(jobRequirementsFilePath, requirements.jobRequirementsPath());
    }

    @Test
    public void testWithoutId() {
        String jobRequirementsFilePath = "Path/test";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withoutId(jobRequirementsFilePath).build();

        assertNull(requirements.identity());
        assertEquals(jobRequirementsFilePath, requirements.jobRequirementsPath());
    }

    @Test
    public void testBuildMissingAttributes() {

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();

        assertThrows(IllegalArgumentException.class, () -> {
            builder.withoutId(null).build();
        });
    }
}
