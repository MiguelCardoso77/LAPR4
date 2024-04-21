package eapli.myclientuser.application;

import eapli.clientusermanagement.domain.SignupRequest;
import eapli.clientusermanagement.domain.SignupRequestBuilder;
import eapli.clientusermanagement.repositories.SignupRequestRepository;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@UseCaseController
public class SignupController {

    private final SignupRequestRepository signupRequestRepository = PersistenceContext.repositories().signupRequests();

    public SignupRequest signup(final String password, final String firstName, final String lastName, final String email, String mecanographicNumber, final Calendar createdOn) {

        final SignupRequestBuilder signupRequestBuilder = UserBuilderHelper.signupBuilder();
        signupRequestBuilder.withUsername(email).withPassword(password)
                .withName(firstName, lastName).withEmail(email).createdOn(createdOn)
                .withMecanographicNumber(mecanographicNumber);

        final SignupRequest newSignupRequest = signupRequestBuilder.build();
        return this.signupRequestRepository.save(newSignupRequest);
    }

    public SignupRequest signup(final String username, final String password,
            final String firstName, final String lastName, final String email,
            String mecanographicNumber) {

        return signup(password, firstName, lastName, email, mecanographicNumber,
        		CurrentTimeCalendars.now());
    }
}
