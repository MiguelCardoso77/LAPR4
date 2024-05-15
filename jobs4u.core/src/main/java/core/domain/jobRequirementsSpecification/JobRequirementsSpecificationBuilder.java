package core.domain.jobRequirementsSpecification;


import eapli.framework.validations.Preconditions;

/**
 * Builder class for creating instances of JobRequirementsSpecification.
 * Allows for fluent construction of JobRequirementsSpecification objects with specified attributes.
 *
 * Usage:
 * JobRequirementsSpecificationBuilder builder = new JobRequirementsSpecificationBuilder();
 * JobRequirementsSpecification requirements = builder.withAll(1, "Bachelor's Degree", 2, "Java programming").build();
 *
 * @author 1220812@isep.ipp.pt
 */

public class JobRequirementsSpecificationBuilder {

    private Integer idRequirements;
    private String academicDegree;

    private int experience;

    private String knowledge;

    /**
     * Sets all attributes required to build a JobRequirementsSpecification.
     *
     * @param idRequirements The ID of the job requirements specification.
     * @param academicDegree The academic degree required for the job.
     * @param experience The minimum years of experience required for the job.
     * @param knowledge The specific knowledge or skills required for the job.
     * @return This JobRequirementsSpecificationBuilder instance.
     */
    public JobRequirementsSpecificationBuilder withAll(final Integer idRequirements, final String academicDegree, final int experience, final String knowledge) {
        this.idRequirements = idRequirements;
        this.academicDegree = academicDegree;
        this.experience = experience;
        this.knowledge = knowledge;
        return this;
    }
    /**
     * Sets all attributes required to build a JobRequirementsSpecification.
     *
     * @param academicDegree The academic degree required for the job.
     * @param experience The minimum years of experience required for the job.
     * @param knowledge The specific knowledge or skills required for the job.
     * @return This JobRequirementsSpecificationBuilder instance.
     */
    public JobRequirementsSpecificationBuilder withoutId( final String academicDegree, final int experience, final String knowledge) {
        this.academicDegree = academicDegree;
        this.experience = experience;
        this.knowledge = knowledge;
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
        Preconditions.nonNull(academicDegree);
        Preconditions.nonNull(experience);
        Preconditions.nonNull(knowledge);

        return new JobRequirementsSpecification(idRequirements, academicDegree, experience, knowledge);
    }
}