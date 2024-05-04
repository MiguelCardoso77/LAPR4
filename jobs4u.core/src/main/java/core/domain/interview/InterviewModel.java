package core.domain.interview;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;
/**
 * Represents an interview model, which is a value object.
 */
@Entity
@Table(name = "INTERVIEW_MODEL")
public class InterviewModel implements AggregateRoot<Integer> {

    @Column(name = "MODEL")
    private String interviewModel;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInterviewModel;

    /**
     * Constructs a new InterviewModel object with the specified interview model value.
     *
     * @param interviewModel The interview model value.
     */
    public InterviewModel(String interviewModel) {
        this.interviewModel = interviewModel;
    }

    public InterviewModel(Integer idInterviewModel, String interviewModel) {
        this.idInterviewModel = idInterviewModel;
        this.interviewModel = interviewModel;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected InterviewModel() {
        // for ORM
    }

    /**
     * Checks if this InterviewModel is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewModel)) {
            return false;
        }

        final InterviewModel that = (InterviewModel) o;
        return this.interviewModel.equals(that.interviewModel);
    }

    /**
     * Returns a hash code value for this InterviewModel.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.interviewModel.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    /**
     * Returns a string representation of this InterviewModel.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return "Model : " + interviewModel;
    }

    @Override
    public Integer identity() {
        return idInterviewModel;
    }
    public String model() {
        return interviewModel;
    }
}
