package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public class InterviewAnswers implements ValueObject {
    private List<String> answers;

    public InterviewAnswers(List<String> answers) {
        this.answers = answers;
    }

    protected InterviewAnswers() {
        // for ORM
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewAnswers)) {
            return false;
        }

        final InterviewAnswers that = (InterviewAnswers) o;
        return this.answers.equals(that.answers);
    }

    @Override
    public int hashCode() {
        return this.answers.hashCode();
    }

    @Override
    public String toString() {
        return answers.toString();
    }
}
