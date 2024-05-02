package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Time implements ValueObject {
    private Integer time;

    public Time(Integer time){
        this.time = time;
    }

    protected Time(){
        // for ORM
    }

    @Override
    public boolean equals(final Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Time)){
            return false;
        }

        final Time that = (Time) o;
        return this.time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }

    @Override
    public String toString() { return "Time = " + time; }
}
