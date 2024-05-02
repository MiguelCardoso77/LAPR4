package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for handling application files.
 *
 * @author 1220812@isep.ipp.pt
 *
 */
public class ApplicationFiles implements ValueObject {

    private List<Path> filesPaths = new ArrayList<>();

    /**
     * Constructs an ApplicationFiles object with the given list of file paths.
     *
     * @param filesPaths The list of file paths
     */
    public ApplicationFiles(final List<Path> filesPaths){
        this.filesPaths = filesPaths;
    }
    /**
     * Protected constructor for ORM use.
     */

    protected ApplicationFiles(){
        // for ORM
    }
    /**
     * Checks if this ApplicationFiles object is equal to another object.
     *
     * @param o The object to compare with
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationFiles that = (ApplicationFiles) o;
        return Objects.equals(filesPaths, that.filesPaths);
    }
    /**
     * Generates a hash code for this ApplicationFiles object.
     *
     * @return The hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(filesPaths);
    }
    /**
     * Returns a string representation of this ApplicationFiles object.
     *
     * @return A string representation of the object
     */
    @Override
    public String toString() {
        return "ApplicationFiles{" +
                "filesPaths=" + filesPaths +
                '}';
    }
}