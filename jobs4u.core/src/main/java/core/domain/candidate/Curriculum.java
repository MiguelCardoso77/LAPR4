package core.domain.candidate;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Curriculum implements ValueObject {
    private String curriculumPath;

    public Curriculum(String curriculumPath) {
        if (curriculumPath == null || curriculumPath.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.curriculumPath = curriculumPath;
    }

    protected Curriculum() {
        // for ORM only
    }

    public String curriculumPath() {
        return this.curriculumPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Curriculum curriculum = (Curriculum) o;
        return curriculumPath.equals(curriculum.curriculumPath);
    }

    @Override
    public int hashCode() {
        return curriculumPath.hashCode();
    }

    @Override
    public String toString() {
        return this.curriculumPath;
    }
}
