package infrastructure.authz;

import eapli.framework.infrastructure.authz.application.Authenticator;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * A credential handler implementation that authenticates users using the authentication service.
 *
 * @author Miguel Cardoso
 */
public class AuthenticationCredentialHandler implements CredentialHandler {
	private final Authenticator authenticationService = AuthzRegistry.authenticationService();

	/**
	 * Authenticates a user with the given username and password, requiring the specified role.
	 *
	 * @param username    the username of the user
	 * @param password    the password of the user
	 * @param onlyWithThis the role required for authentication
	 * @return true if the authentication is successful, false otherwise
	 */
	@Override
	public boolean authenticated(String username, String password, Role onlyWithThis) {
		return authenticationService.authenticate(username, password, onlyWithThis).isPresent();
	}
}
