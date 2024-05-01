package infrastructure.authz;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * Interface for handling user authentication.
 *
 * @author Miguel Cardoso
 */
public interface CredentialHandler {
	/**
	 * Authenticates a user with the given username and password.
	 *
	 * @param username    the username of the user
	 * @param password    the password of the user
	 * @param onlyWithThis the role required for authentication
	 * @return true if the authentication is successful, false otherwise
	 */
	boolean authenticated(String username, String password, Role onlyWithThis);
}
