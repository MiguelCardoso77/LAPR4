package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;


/**
 * Represents a score, which is a value object.
 *
 * @author Tomás Gonçalves
 */
@Embeddable
public class Score implements ValueObject {

    private Integer score;

    /**
     * Constructs a new Score object with the specified score value.
     *
     * @param score The score value.
     */

    public Score(Integer score){
        this.score = score;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected Score(){
        // for ORM
    }


    /**
     * Checks if this Score is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Score)) {
            return false;
        }

        final Score that = (Score) o;
        return this.score.equals(that.score);
    }

    /**
     * Returns a hash code value for this Score.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.score.hashCode();
    }


    /**
     * Returns a string representation of this Score.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return "Score = " + score; }

    /**
     * Retrieves the score value.
     *
     * @return The score value.
     */
    public Integer getScore() {
        return score;
    }
}
