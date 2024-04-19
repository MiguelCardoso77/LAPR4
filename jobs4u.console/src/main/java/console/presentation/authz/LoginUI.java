package console.presentation.authz;

import infrastructure.authz.CredentialHandler;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Collection;

/** 
 * UI for user login action. 
 *
 * @author nuno on 21/03/16.
 */
@SuppressWarnings("squid:S106")
public class LoginUI extends AbstractUI {

	private Role[] validRoles = new Role[4];
	private Role onlyWithThis;
	private static final int DEFAULT_MAX_ATTEMPTS = 3;
	private final int maxAttempts;

	private final CredentialHandler credentialHandler;

	public LoginUI(CredentialHandler credentialHandler) {
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis) {
		this.onlyWithThis = onlyWithThis;
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis, final int maxAttempts) {
		this.onlyWithThis = onlyWithThis;
		this.maxAttempts = maxAttempts;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, Role role1, Role role2, Role role3, Role role4) {
		this.validRoles[0] = role1;
		this.validRoles[1] = role2;
		this.validRoles[2] = role3;
		this.validRoles[3] = role4;
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final int maxAttempts) {
		this.maxAttempts = maxAttempts;
		this.credentialHandler = credentialHandler;
	}

	@Override
	protected boolean doShow() {
		var attempt = 1;
		while (attempt <= maxAttempts) {
			final String userName = Console.readNonEmptyLine("Username:", "Please provide a username");
			final String password = Console.readLine("Password:");

			if (credentialHandler.authenticated(userName, password, onlyWithThis)) {
				return true;
			}
			System.out.printf("Wrong username or password. You have %d attempts left.%n%n»»»»»»»»»%n", maxAttempts - attempt);
			attempt++;
		}
		System.out.println("Sorry, we are unable to authenticate you. Please contact your system administrator.");
		return false;
	}

	@Override
	public String headline() {
		return "Login";
	}
}
