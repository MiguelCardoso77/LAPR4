package core.domain.jobRequirementsSpecification;

public enum ProgrammingLanguages {

    JAVA ("Java"),
    JAVASCRIPT  ("JavaScript") ,
    PYTHON  ("Python"),
    TYPESCRIPT ("TypeScript")  ,
    PHP ("PHP"),
    CHASH ("C#") ;



    private final String usage;

    ProgrammingLanguages(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return usage;
    }
}
