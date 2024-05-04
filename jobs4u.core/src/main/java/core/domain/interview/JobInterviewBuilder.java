package core.domain.interview;

import core.domain.application.Application;
import eapli.framework.domain.model.DomainFactory;

import java.util.Calendar;

public class JobInterviewBuilder implements DomainFactory<JobInterview> {

    private int id;
    private Calendar createdOn;
    private Time time;
    private Score score;
    private Result result;
    private Application application;
    private InterviewModel interviewModel;

    public JobInterviewBuilder withAll(Calendar createdOn, int time, int score, String result,
                                       Application application, InterviewModel interviewModel) {
        this.createdOn = createdOn;
        this.time = new Time(time);
        this.score = new Score(score);
        this.result = new Result(result);
        this.application = application;
        this.interviewModel = null;
        return this;
    }

    @Override
    public JobInterview build() {
        return new JobInterview(createdOn, time, score, result, application, null);
    }

}
