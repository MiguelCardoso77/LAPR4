package core.domain.interview;

import core.domain.application.Application;
import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;

/**
 * Builder class for creating instances of {@link JobInterview}.
 *
 * @author Diana Neves
 */
public class JobInterviewBuilder implements DomainFactory<JobInterview> {
    private Calendar createdOn;
    private Time time;
    private Score score;
    private Result result;
    private Application application;

    /**
     * Sets all attributes of the builder.
     *
     * @param createdOn   the date when the interview was created
     * @param time        the time of the interview
     * @param score       the score of the interview
     * @param result      the result of the interview
     * @param application the application associated with the interview
     * @return this builder instance
     */
    public JobInterviewBuilder withAll(Calendar createdOn, int time, int score, String result, Application application) {
        this.createdOn = createdOn;
        this.time = new Time(time);
        this.score = new Score(score);
        this.result = new Result(result);
        this.application = application;

        return this;
    }

    /**
     * Builds a new instance of {@link JobInterview}.
     *
     * @return the built {@link JobInterview} instance
     */
    @Override
    public JobInterview build() {
        return new JobInterview(createdOn, time, score, result, application, null);
    }
}