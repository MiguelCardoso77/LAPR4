package core.domain.jobRequirementsSpecification;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "JOB_REQUIREMENTS_SPECIFICATION")
public class JobRequirementsSpecification implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRequirements;

    @Column(name = "REQUIREMENTS")
    private Requirements requirements;

    public JobRequirementsSpecification(Requirements requirements , Integer idRequirements) {
        this.idRequirements = idRequirements;
        this.requirements = requirements;
    }

    public JobRequirementsSpecification(){
        //for ORM only
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobRequirementsSpecification that = (JobRequirementsSpecification) o;
        return idRequirements == that.idRequirements && Objects.equals(requirements, that.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequirements, requirements);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }


    public Requirements requirements() {
        return requirements;
    }


    @Override
    public Integer identity() {
        return idRequirements;
    }

    @Override
    public boolean hasIdentity(Integer idRequirements) {
        return AggregateRoot.super.hasIdentity(idRequirements);
    }
}


