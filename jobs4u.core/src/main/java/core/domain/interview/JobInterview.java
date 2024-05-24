package core.domain.interview;

import core.domain.application.Application;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "JOB_INTERVIEW")
public class JobInterview implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    @Column(name = "INTERVIEW_TIME")
    private Time time;

    @Column(name = "SCORE")
    private  Score score;

    @Column(name = "RESULT")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "INTERVIEW_MODEL")
    private InterviewModel interviewModel;

    @Column(name = "INTERVIEW_ANSWERS")
    private InterviewAnswers interviewAnswers;

    /**
     * Constructs a job interview with the specified attributes.
     *
     * @param createdOn      the date when the interview was created
     * @param time           the time of the interview
     * @param score          the score of the interview
     * @param result         the result of the interview
     * @param application    the application associated with the interview
     * @param interviewModel the interview model used for the interview
     */
    public JobInterview(Calendar createdOn, Time time, Score score, Result result, Application application, InterviewModel interviewModel, InterviewAnswers interviewAnswers) {
        this.createdOn = createdOn;
        this.time = time;
        this.score = score;
        this.result = result;
        this.application = application;
        this.interviewModel = interviewModel;
        this.interviewAnswers = interviewAnswers;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected JobInterview() {
        // for ORM only
    }

    /**
     * Retrieves the identity of this job interview.
     *
     * @return the identity of this job interview
     */
    public Integer identity() {
        return id;
    }

    /**
     * Retrieves the date when the interview was created.
     *
     * @return the date when the interview was created
     */
    public Calendar createdOn() {
        return createdOn;
    }

    /**
     * Retrieves the time of the interview.
     *
     * @return the time of the interview
     */
    public Time time() {
        return time;
    }

    /**
     * Retrieves the score of the interview.
     *
     * @return the score of the interview
     */
    public Score score() {
        return score;
    }

    /**
     * Retrieves the result of the interview.
     *
     * @return the result of the interview
     */
    public Result result() {
        return result;
    }

    /**
     * Retrieves the application associated with the interview.
     *
     * @return the application associated with the interview
     */
    public Application application() {
        return application;
    }

    /**
     * Retrieves the interview model used for the interview.
     *
     * @return the interview model used for the interview
     */
    public InterviewModel interviewModel() {
        return interviewModel;
    }

    public InterviewAnswers interviewAnswers() {
        return interviewAnswers;
    }

    /**
     * Changes the interview model used for the interview.
     *
     * @param interviewModelToChange the new interview model
     */
    public void updateInterviewModel(InterviewModel interviewModelToChange) {
        if (interviewModelToChange != null) {
            this.interviewModel = interviewModelToChange;
        } else {
            System.out.println("not able to change it");
        }
    }

    public void uploadInterviewAnswers(InterviewAnswers interviewAnswers) {
        Preconditions.nonNull(interviewAnswers, "Interview answers cannot be null");
        this.interviewAnswers = interviewAnswers;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInterview jobInterview = (JobInterview) o;
        return Objects.equals(id, jobInterview.id) && Objects.equals(createdOn, jobInterview.createdOn)
                && Objects.equals(time, jobInterview.time) && Objects.equals(score, jobInterview.score)
                && Objects.equals(result, jobInterview.result) && Objects.equals(application, jobInterview.application)
                && Objects.equals(interviewModel, jobInterview.interviewModel);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, time, score, result, application);
    }

    /**
     * Checks whether this object is the same as another object.
     *
     * @param other the object to compare to
     * @return true if the objects are the same, false otherwise
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param other the object to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }
    /**
     * Returns a string representation of the JobInterview object.
     * This implementation returns a string that includes the values of
     * the id, createdOn, time, score, result, application, interviewModel,
     * and interviewAnswers fields.
     *
     * @return A string representation of the JobInterview object.
     */
    @Override
    public String toString() {
        return "JobInterview{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", time=" + time +
                ", score=" + score +
                ", result=" + result +
                ", application=" + application +
                ", interviewModel=" + interviewModel +
                ", interviewAnswers=" + interviewAnswers +
                '}';
    }
    /**
     * Updates the score of this JobInterview with a new score.
     *
     * @param newScore The new score to be set.
     */
    public void updateScore(Score newScore){
        score = newScore;
    }

    public Score getScore(Object o) {
        return score;
    }

    public  int returnScore() {
        return score.getScore();
    }


}
