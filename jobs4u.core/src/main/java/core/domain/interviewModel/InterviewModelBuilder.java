package core.domain.interviewModel;

import eapli.framework.validations.Preconditions;

/**
 * Builds an InterviewModel object.
 * This class provides methods for setting the model of an InterviewModel and building an InterviewModel object.
 *
 * @author Diana Neves
 */
public class InterviewModelBuilder {
    private String model;

    /**
     * Sets the model of the InterviewModel to be built.
     *
     * @param model The model of the InterviewModel.
     */
    public void withoutId(final String model) {
        this.model = model;
    }

    /**
     * Builds an InterviewModel object with the specified model.
     *
     * @return An InterviewModel object.
     */
    public InterviewModel build() {
        Preconditions.nonNull(model);
        return new InterviewModel(model);
    }
}
