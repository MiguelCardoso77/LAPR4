package core.domain.jobRequirementsSpecification;

/**
 * Represents the academic degree required for a job.
 * This enum provides different levels of academic degrees that can be required for a job.
 *
 * @author Tomás Gonçalves
 */
public enum AcademicDegree {
    NONE("None"),
    BACHELOR("Bachelor"),
    MASTER("Master"),
    DOCTORATE("Doctorate");
    private final String usage;

    /**
     * Constructs an AcademicDegree with the specified usage.
     *
     * @param usage The usage of the academic degree.
     */
    AcademicDegree(String usage) {
        this.usage = usage;
    }

    /**
     * Returns a string representation of the academic degree.
     *
     * @return A string representation of the academic degree.
     */
    @Override
    public String toString() {
        return usage;
    }

}