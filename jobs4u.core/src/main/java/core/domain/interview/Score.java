package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Score implements ValueObject {

    private Integer score;

    public Score(Integer score){
        this.score = score;
    }

    protected Score(){
        // for ORM
    }

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

    @Override
    public int hashCode() {
        return this.score.hashCode();
    }


    @Override
    public String toString() {
        return "Score = " + score; }
}
