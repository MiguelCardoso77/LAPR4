package core.domain.jobRequirementsSpecification;
/**
 * Represents the programming languages required for a job.
 * This enum provides different programming languages that can be required for a job.
 *
 * @author Tomás Gonçalves
 */
public enum ProgrammingLanguages {
    JAVA ("Java"),
    JAVASCRIPT  ("JavaScript") ,
    PYTHON  ("Python"),
    TYPESCRIPT ("TypeScript")  ,
    PHP ("PHP"),
    CHASH ("C#") ;
    private final String usage;
    /**
     * Constructs a ProgrammingLanguages with the specified usage.
     *
     * @param usage The usage of the programming language.
     */
    ProgrammingLanguages(String usage) {
        this.usage = usage;
    }
    /**
     * Returns a string representation of the programming language.
     *
     * @return A string representation of the programming language.
     */
    @Override
    public String toString() {
        return usage;
    }
}