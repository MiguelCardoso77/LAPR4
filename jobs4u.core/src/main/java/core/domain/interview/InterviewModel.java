package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class InterviewModel implements ValueObject {
    private String interviewModel;

    public InterviewModel(String interviewModel) {
        this.interviewModel = interviewModel;
    }

    protected InterviewModel() {
        // for ORM
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewModel)) {
            return false;
        }

        final InterviewModel that = (InterviewModel) o;
        return this.interviewModel.equals(that.interviewModel);
    }

    @Override
    public int hashCode() {
        return this.interviewModel.hashCode();
    }

    @Override
    public String toString() {
        return interviewModel;
    }

}
