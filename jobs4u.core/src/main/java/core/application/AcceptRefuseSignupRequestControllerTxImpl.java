package core.application;

import core.domain.customer.SignupRequest;
import core.repositories.CustomerRepository;
import core.repositories.SignupRequestRepository;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashSet;
import java.util.Set;

/**
 * The transactional controller for the use case "accept/refuse a signup
 * request".
 * <p>
 * following the guideline that a controller should only change one Aggregate,
 * we shouldn't be changing all these entities here, but should instead use
 * asynchronous events. However in this case we will take advantage of
 * TransactionalContext
 *
 * @todo handle the scenario where in the meantime the username is already used
 *       by some other user
 *
 * @author AJS on 08/04/2016.
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerTxImpl implements AcceptRefuseSignupRequestController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final TransactionalContext txCtx = PersistenceContext.repositories()
            .newTransactionalContext();
    private final CustomerRepository customerRepository = PersistenceContext
            .repositories().customerUsers(txCtx);
    private final SignupRequestRepository signupRequestsRepository = PersistenceContext
            .repositories().signupRequests(txCtx);

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#acceptSignupRequest(eapli.base.
     * clientusermanagement.domain.SignupRequest)
     */
    @Override
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        txCtx.beginTransaction();

        final SystemUser newUser = createSystemUserForClientUser(theSignupRequest);
        //createClientUser(theSignupRequest, newUser);
        theSignupRequest = acceptTheSignupRequest(theSignupRequest);

        // explicitly commit the transaction
        txCtx.commit();

        return theSignupRequest;
    }

    private SignupRequest acceptTheSignupRequest(final SignupRequest theSignupRequest) {
        theSignupRequest.accept();
        return this.signupRequestsRepository.save(theSignupRequest);
    }
    /*

    private void createClientUser(final SignupRequest theSignupRequest,
                                  final SystemUser newUser, final TelephoneNumber telephoneNumber) {
        final CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.withTelephoneNumber(telephoneNumber)
                .withSystemUser(newUser);
        this.customerRepository.save(customerBuilder.build());
    }

     */

    //
    // add system user
    //
    private SystemUser createSystemUserForClientUser(final SignupRequest theSignupRequest) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CLIENT_USER);
        return userService.registerUser(theSignupRequest.username(), theSignupRequest.password(),
                theSignupRequest.name(), theSignupRequest.email(), roles);
    }

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#refuseSignupRequest(eapli.base.
     * clientusermanagement.domain.SignupRequest)
     */
    @Override
    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        txCtx.beginTransaction();

        theSignupRequest.refuse();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);

        // explicitly commit the transaction
        txCtx.commit();

        return theSignupRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#listPendingSignupRequests()
     */
    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
