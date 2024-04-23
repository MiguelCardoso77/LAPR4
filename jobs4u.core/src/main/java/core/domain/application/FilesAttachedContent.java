package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class FilesAttachedContent implements ValueObject {
    private String filesAttachedContent;

    public FilesAttachedContent(final String filesAttachedContent) {
        if (filesAttachedContent == null || filesAttachedContent.isEmpty()) {
            throw new IllegalArgumentException("FilesAttachedContent should neither be null nor empty");
        }

        this.filesAttachedContent = filesAttachedContent;
    }

    protected FilesAttachedContent() {
        // for ORM
    }

    public static FilesAttachedContent valueOf(final String FilesAttachedContent) {
        return new FilesAttachedContent(FilesAttachedContent);
    }

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

    @Override
    public int hashCode() {
        return this.filesAttachedContent.hashCode();
    }

    @Override
    public String toString() {
        return filesAttachedContent;
    }
}


