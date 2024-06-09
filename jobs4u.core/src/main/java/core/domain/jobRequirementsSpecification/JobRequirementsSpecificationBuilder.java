package core.domain.jobRequirementsSpecification;


import eapli.framework.validations.Preconditions;

/**
 * Builder class for creating instances of JobRequirementsSpecification.
 * Allows for fluent construction of JobRequirementsSpecification objects with specified attributes.
 * Usage:
 * JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
 * JobRequirementsSpecification requirements = builder.withAll(1, "Bachelor's Degree", 2, "Java programming").build();
 *
 * @author 1220812@isep.ipp.pt
 */

public class JobRequirementsSpecificationBuilder {
    private Integer idRequirements;
    private String jobRequirementsFile;

    /**
     * Sets all attributes required to build a JobRequirementsSpecification.
     *
     * @param idRequirements The ID of the job requirements specification.
     * @param jobRequirementsPath The path for the job requirements specification file.
     * @return This JobRequirementsSpecificationBuilder instance.
     */
    public JobRequirementsSpecificationBuilder withAll(final Integer idRequirements, final String jobRequirementsPath) {
        this.idRequirements = idRequirements;
        this.jobRequirementsFile = jobRequirementsPath;
        return this;
    }
    /**
     * Sets all attributes required to build a JobRequirementsSpecification.
     *
     * @param jobRequirementsFile The path for the job requirements specification file.
     * @return This JobRequirementsSpecificationBuilder instance.
     */
    public JobRequirementsSpecificationBuilder withoutId( final String jobRequirementsFile) {
        this.jobRequirementsFile = jobRequirementsFile;
        return this;
    }

    /**
     * Builds a new JobRequirementsSpecification based on the provided attributes.
     * Preconditions ensure that all required attributes are set.
     *
     * @return The constructed JobRequirementsSpecification object.
     * @throws IllegalStateException if any required attribute is not set.
     */
    public JobRequirementsSpecification build() {
        Preconditions.nonNull(jobRequirementsFile);
        return new JobRequirementsSpecification(idRequirements, jobRequirementsFile);
    }
}