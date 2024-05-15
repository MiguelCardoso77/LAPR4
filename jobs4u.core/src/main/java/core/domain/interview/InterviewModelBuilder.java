package core.domain.interview;

import eapli.framework.validations.Preconditions;

public class InterviewModelBuilder {

    private String model;

    public InterviewModelBuilder withoutId(final String model){
        this.model = model;
        return this;
    }

    public InterviewModel build() {
        Preconditions.nonNull(model);

        return new InterviewModel(model);
    }
}
