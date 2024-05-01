package core.domain.jobRequirementsSpecification;

import eapli.framework.domain.model.ValueObject;
import java.util.Objects;

public class Requirements implements ValueObject{

    private Integer experienceYears;
    private String degree;
    private String knowledge;


    public Requirements(final Integer experienceYears , final String degree , final String knowledge) {
        if (experienceYears == null || degree == null || knowledge == null || degree.isEmpty() || knowledge.isEmpty()){
            throw new IllegalArgumentException("experienceYears cannot be null or empty");
        }

        this.experienceYears = experienceYears;
        this.degree = degree;
        this.knowledge = knowledge;

    }

    protected Requirements(){
        //for ORM
    }

    public static Requirements valueOf(Integer experienceYears , final String degree , final String knowledge) {
        return new Requirements(experienceYears , degree , knowledge);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Requirements)) {
            return false;
        }

        final Requirements that = (Requirements) o;

        return this.experienceYears.equals(that.experienceYears) && this.degree.equals(that.degree) && this.knowledge.equals(that.knowledge);

    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceYears, degree, knowledge);
    }

    @Override
    public String toString() {
        return "Requirements:" +
                "\nexperienceYears:" + experienceYears +
                "\ndegree:" + degree +
                "\nknowledge: " + knowledge +
                ';';
    }


}
