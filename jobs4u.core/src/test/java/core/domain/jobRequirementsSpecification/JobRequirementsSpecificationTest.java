package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class JobRequirementsSpecificationTest {

    @Test
    public void testConstructor() {
        String jobRequirementsSpecificationFilePath = "JobRequirements/path/test";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withoutId(jobRequirementsSpecificationFilePath).build();;

        assertEquals(jobRequirementsSpecificationFilePath, requirements.jobRequirementsPath());
    }

    @Test
    public void testEqualsAndHashCode() {
        String jobRequirementsSpecificationFilePath = "JobRequirements/path/test";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withoutId(jobRequirementsSpecificationFilePath).build();;
        JobRequirementsSpecification jobReqSpec1 = builder.withoutId(jobRequirementsSpecificationFilePath).build();


        assertEquals(jobReqSpec1, requirements);
        assertEquals(jobReqSpec1.hashCode(), requirements.hashCode());
    }

    @Test
    public void testGetters() {
        String jobRequirementsSpecificationFilePath = "JobRequirements/path/test";

        JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
        JobRequirementsSpecification requirements = builder.withoutId(jobRequirementsSpecificationFilePath).build();;

        assertEquals("JobRequirements/path/test", requirements.jobRequirementsPath());
    }
}