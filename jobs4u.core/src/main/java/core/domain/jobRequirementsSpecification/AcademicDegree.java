package core.domain.jobRequirementsSpecification;



public enum AcademicDegree {


    NONE ("None") ,
    BACHELOR ("Bachelor") ,
    MASTER ("Master")  ,
    DOCTORATE ("Doctorate") ;

    private final String usage;

    AcademicDegree(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return usage;
    }

}