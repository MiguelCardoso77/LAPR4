package core.domain.interview;

import core.domain.application.Application;
import eapli.framework.domain.model.DomainFactory;
import java.util.Calendar;

public class JobInterviewBuilder implements DomainFactory<JobInterview> {

    private int id;
    private Calendar createdOn;
    private int time;
    private int score;
    private String result;
    private Application application;

    public JobInterviewBuilder withAll(int id, Calendar createdOn, int time, int score, String result, Application application){
        this.id = id;
        this.createdOn = createdOn;
        this.time = time;
        this.score = score;
        this.result = result;
        this.application = application;
        return this;
    }

    @Override
    public JobInterview build() {
        return new JobInterview(id, createdOn, time, score, result, application);
    }
}
