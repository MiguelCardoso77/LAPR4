package core.domain.interview;
import core.domain.application.Application;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
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
    private int time;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "RESULT")
    private String result;

    @ManyToOne
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;


    public JobInterview(int id, Calendar createdOn, int time, int score, String result, Application application){
        this.id = id;
        this.createdOn = createdOn;
        this.time = time;
        this.score = score;
        this.result = result;
        this.application = application;
    }

    protected JobInterview(){
        // for ORM only
    }

    public Integer identity(){ return id;}

    public Calendar createdOn() {
        return createdOn;
    }

    public int time() {
        return time;
    }

    public int score() {
        return score;
    }

    public String result() {
        return result;
    }

    public Application application() { return application; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInterview jobInterview = (JobInterview) o;
        return Objects.equals(id, jobInterview.id) && Objects.equals(createdOn, jobInterview.createdOn)
                && Objects.equals(time, jobInterview.time) && Objects.equals(score, jobInterview.score)
                && Objects.equals(result, jobInterview.result) && Objects.equals(application, jobInterview.application);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn, time, score, result, application);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }
}
