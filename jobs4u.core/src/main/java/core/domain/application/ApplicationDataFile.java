package core.domain.application;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents a data file associated with an application.
 * This value object encapsulates the file name or path for an application's data.
 *
 * @author Tomás Gonçalves
 */
public class ApplicationDataFile implements ValueObject {
    private String applicationDataFile;

    /**
     * Constructs an `ApplicationDataFile` with the given file name or path.
     *
     * @param applicationDataFile the file name or path (must not be null or empty)
     * @throws IllegalArgumentException if the file name or path is null or empty
     */
    public ApplicationDataFile(final String applicationDataFile) {
        if (applicationDataFile == null || applicationDataFile.isEmpty()) {
            throw new IllegalArgumentException("applicationDataFile should neither be null nor empty");
        }

        this.applicationDataFile = applicationDataFile;
    }

    /**
     * Default constructor for ORM mapping.
     */
    protected ApplicationDataFile() {
        // for ORM
    }

    /**
     * Creates an `ApplicationDataFile` from the given file name or path.
     *
     * @param applicationDataFile the file name or path
     * @return the corresponding `ApplicationDataFile` instance
     */
    public static ApplicationDataFile valueOf(final String applicationDataFile) {
        return new ApplicationDataFile(applicationDataFile);
    }

    /**
     * Checks if this `ApplicationDataFile` is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationDataFile)) {
            return false;
        }

        final ApplicationDataFile that = (ApplicationDataFile) o;
        return this.applicationDataFile.equals(that.applicationDataFile);
    }

    /**
     * Computes the hash code for this `ApplicationDataFile`.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return this.applicationDataFile.hashCode();
    }

    /**
     * Returns a string representation of this `ApplicationDataFile`.
     *
     * @return the string representation (the file name or path)
     */
    @Override
    public String toString() {
        return applicationDataFile;
    }
}


