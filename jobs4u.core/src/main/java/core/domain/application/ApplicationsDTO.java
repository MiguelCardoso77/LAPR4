package core.domain.application;

import eapli.framework.representations.dto.DTO;

/**
 * Data Transfer Object (DTO) for applications.
 * This class serves as a simple container for transferring application data between different layers of the application.
 * It includes the name of the application and the number of applicants.
 * The class also provides methods to access these properties and a custom string representation.
 * The {@code ApplicationsDTO} class is annotated with {@code @DTO} to indicate its role as a Data Transfer Object.
 *
 * @author Diana Neves
 */
@DTO
public class ApplicationsDTO {
    public String application;
    public Integer applicants;

    /**
     * Constructs a new ApplicationsDTO with the specified application name and number of applicants.
     *
     * @param application the name of the application.
     * @param applicants  the number of applicants.
     */
    public ApplicationsDTO(final String application, final Integer applicants) {
        this.application = application;
        this.applicants = applicants;
    }

    /**
     * Gets the name of the application.
     *
     * @return the application name.
     */
    public String myApplication() {
        return application;
    }

    /**
     * Gets the number of applicants.
     *
     * @return the number of applicants.
     */
    public Integer myApplicants() {
        return applicants;
    }

    /**
     * Returns a string representation of the application and the number of applicants.
     *
     * @return a string containing the application name and number of applicants.
     */
    @Override
    public String toString() {
        return application + "Number of Applicants: " + applicants;
    }

}
