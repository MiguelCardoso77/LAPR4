package core.domain.application;

import eapli.framework.representations.dto.DTO;

@DTO
public class ApplicationsDTO {
    public String application;
    public Integer applicants;


    public ApplicationsDTO(final String application, final Integer applicants) {
        this.application = application;
        this.applicants = applicants;
    }

    public String myApplication() {
        return application;
    }

    public Integer myApplicants() {
        return applicants;
    }

    @Override
    public String toString() {
        return application + "Number of Applicants: " + applicants;
    }

}
