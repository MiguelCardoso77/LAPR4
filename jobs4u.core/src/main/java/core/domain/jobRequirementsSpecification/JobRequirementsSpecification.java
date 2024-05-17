package core.domain.jobRequirementsSpecification;


import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 * Represents the specifications for job requirements in a company.
 *
 * @author 1220812@isep.ipp.pt
 */

@Entity
@Table(name = "JOB_REQUIREMENTS")
public class JobRequirementsSpecification implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRequirements;

    @Column(name = "JOB_REQUIREMENTS_PATH")
    private String jobRequirementsSpecificationFile;
    /**
     * Constructs a new JobRequirementsSpecification with the specified academic degree, experience, and knowledge.
     *
     * @param id             The Job Requirements specification identifier
     * @param jobRequirementsSpecificationFile The Job Requirements Specification path
     */
    public JobRequirementsSpecification(final Integer id, final String jobRequirementsSpecificationFile) {
        this.idRequirements = id;
        this.jobRequirementsSpecificationFile = jobRequirementsSpecificationFile;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected JobRequirementsSpecification(){
        //for ORM only
    }
    /**
     * Compares this JobRequirementsSpecification to the specified object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobRequirementsSpecification that = (JobRequirementsSpecification) o;
        return idRequirements == that.idRequirements && jobRequirementsSpecificationFile == that.jobRequirementsSpecificationFile;
    }
    /**
     * Returns a hash code value for this JobRequirementsSpecification.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idRequirements, jobRequirementsSpecificationFile);
    }
    /**
     * Checks if this JobRequirementsSpecification is the same as another object.
     *
     * @param other The object to compare to.
     * @return True if this object is the same as the other object, false otherwise.
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        JobRequirementsSpecification that = (JobRequirementsSpecification) other;
        return Objects.equals(idRequirements, that.idRequirements);
    }
    /**
     * Compares this JobRequirementsSpecification to the specified id.
     *
     * @param other The id to compare to.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified id.
     */
    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }

    public String jobRequirementsPath(){
        return this.jobRequirementsSpecificationFile;
    }

    /**
     * Retrieves the identity of this JobRequirementsSpecification.
     *
     * @return The identity of this object.
     */
    @Override
    public Integer identity() {
        return idRequirements;
    }
    /**
     * Checks if this JobRequirementsSpecification has the specified identity.
     *
     * @param idRequirements The identity to check.
     * @return True if this object has the specified identity, false otherwise.
     */
    @Override
    public boolean hasIdentity(Integer idRequirements) {
        return AggregateRoot.super.hasIdentity(idRequirements);
    }

    @Override
    public String toString() {
        return "idRequirements=" + idRequirements +
                ", jobRequirementsSpecificationFile='" + jobRequirementsSpecificationFile;
    }
}