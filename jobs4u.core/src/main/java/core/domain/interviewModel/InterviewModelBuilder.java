package core.domain.interviewModel;

import eapli.framework.validations.Preconditions;

public class InterviewModelBuilder {

    private String model;

    public void withoutId(final String model){
        this.model = model;
    }

    public InterviewModel build() {
        Preconditions.nonNull(model);

        return new InterviewModel(model);
    }
}
