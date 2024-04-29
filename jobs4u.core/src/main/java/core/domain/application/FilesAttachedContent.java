package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the content of files attached to an email.
 * This class is a value object.
 *
 * @author Tomás Gonçalves
 */
@Embeddable
public class FilesAttachedContent implements ValueObject {
    private String filesAttachedContent;

    /**
     * Constructs a `FilesAttachedContent` instance.
     *
     * @param filesAttachedContent The content of files attached.
     * @throws IllegalArgumentException if `filesAttachedContent` is null or empty.
     */
    public FilesAttachedContent(final String filesAttachedContent) {
        if (filesAttachedContent == null || filesAttachedContent.isEmpty()) {
            throw new IllegalArgumentException("FilesAttachedContent should neither be null nor empty");
        }

        this.filesAttachedContent = filesAttachedContent;
    }

    /**
     * Default constructor for ORM (Object-Relational Mapping).
     */
    protected FilesAttachedContent() {
        // for ORM
    }

    /**
     * Creates a `FilesAttachedContent` instance from a given string.
     *
     * @param filesAttachedContent The content of files attached.
     * @return A `FilesAttachedContent` instance.
     */
    public static FilesAttachedContent valueOf(final String filesAttachedContent) {
        return new FilesAttachedContent(filesAttachedContent);
    }

    /**
     * Checks if this `FilesAttachedContent` is equal to another object.
     *
     * @param o The object to compare.
     * @return `true` if equal, otherwise `false`.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilesAttachedContent)) {
            return false;
        }

        final FilesAttachedContent that = (FilesAttachedContent) o;
        return this.filesAttachedContent.equals(that.filesAttachedContent);
    }

    /**
     * Computes the hash code for this `FilesAttachedContent`.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this.filesAttachedContent.hashCode();
    }

    /**
     * Returns the string representation of this `FilesAttachedContent`.
     *
     * @return The content of files attached.
     */
    @Override
    public String toString() {
        return filesAttachedContent;
    }
}
