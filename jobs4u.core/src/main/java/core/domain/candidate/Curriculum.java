package core.domain.candidate;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the curriculum of a candidate.
 *
 * @author Miguel Cardoso
 */
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

    /**
     * Creates a new instance of {@link Curriculum} with the given path.
     *
     * @return the path to the curriculum file
     */
    public String curriculumPath() {
        return this.curriculumPath;
    }

    /**
     * Equality is based on the curriculum path.
     * @param o object
     * @return true if the curriculum paths are equal, false otherwise
     */
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

    /**
     * Hash code is based on the curriculum path.
     *
     * @return the hash code of the curriculum path
     */
    @Override
    public int hashCode() {
        return curriculumPath.hashCode();
    }

    /**
     * Returns the curriculum path.
     *
     * @return the curriculum path
     */
    @Override
    public String toString() {
        return this.curriculumPath;
    }
}
