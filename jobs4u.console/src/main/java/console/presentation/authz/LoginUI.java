package console.presentation.authz;

import core.protocol.ServerAuthentication;
import infrastructure.authz.CredentialHandler;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI for user login action.
 * Allows users to authenticate with their credentials.
 * Provides options for specifying valid roles and maximum login attempts.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public class LoginUI extends AbstractUI {

    private Role onlyWithThis;
    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private final int maxAttempts;
    private boolean serverUsage;
    private String email;

    private final CredentialHandler credentialHandler;

    /**
     * Constructs a LoginUI instance with a default maximum number of login attempts.
     *
     * @param credentialHandler the credential handler to use for authentication
     */
    public LoginUI(CredentialHandler credentialHandler) {
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
        this.credentialHandler = credentialHandler;
    }

    /**
     * Constructs a LoginUI instance with a specified role and a default maximum number of login attempts.
     *
     * @param credentialHandler the credential handler to use for authentication
     * @param onlyWithThis      the role to restrict login to
     */
    public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis) {
        this.onlyWithThis = onlyWithThis;
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
        this.credentialHandler = credentialHandler;
    }

    /**
     * Constructs a LoginUI instance with a specified role and maximum number of login attempts.
     *
     * @param credentialHandler the credential handler to use for authentication
     * @param onlyWithThis      the role to restrict login to
     * @param maxAttempts       the maximum number of login attempts
     */
    public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis, final int maxAttempts) {
        this.onlyWithThis = onlyWithThis;
        this.maxAttempts = maxAttempts;
        this.credentialHandler = credentialHandler;
    }

    public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis, boolean server) {
        this.onlyWithThis = onlyWithThis;
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
        this.credentialHandler = credentialHandler;
        this.serverUsage = server;
    }

    /**
     * Constructs a LoginUI instance with a list of valid roles and a default maximum number of login attempts.
     *
     * @param credentialHandler the credential handler to use for authentication
     * @param role1             the first valid role
     * @param role2             the second valid role
     * @param role3             the third valid role
     * @param role4             the fourth valid role
     */
    public LoginUI(CredentialHandler credentialHandler, Role role1, Role role2, Role role3, Role role4) {
        Role[] validRoles = new Role[4];
        validRoles[0] = role1;
        validRoles[1] = role2;
        validRoles[2] = role3;
        validRoles[3] = role4;
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
        this.credentialHandler = credentialHandler;
    }

    /**
     * Constructs a LoginUI instance with a specified maximum number of login attempts.
     *
     * @param credentialHandler the credential handler to use for authentication
     * @param maxAttempts       the maximum number of login attempts
     */
    public LoginUI(CredentialHandler credentialHandler, final int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.credentialHandler = credentialHandler;
    }

    /**
     * Displays the login user interface and handles user authentication.
     * Users are prompted to enter their email and password, and their credentials are authenticated.
     * The user's session is established if authentication is successful.
     *
     * @return true if authentication is successful, false otherwise
     */
    @Override
    protected boolean doShow() {
        int attempt = 1;

        if (!serverUsage) {

            while (attempt <= maxAttempts) {
                final String userName = Console.readNonEmptyLine("Email:", "Please provide an email address.");
                final String password = Console.readLine("Password:");


                if (credentialHandler.authenticated(userName, password, onlyWithThis)) {
                    return true;
                }

                System.out.printf("Wrong username or password. You have %d attempts left.%n%n»»»»»»»»»%n", maxAttempts - attempt);
                attempt++;

            }

        } else {

            final String email = Console.readNonEmptyLine("Email:", "Please provide an email address.");
            final String password = Console.readLine("Password:");

            ServerAuthentication serverAuthentication = new ServerAuthentication();
            if (serverAuthentication.authenticateCandidate(email, password)) {
                this.email = email;
                return true;
            }

        }

        System.out.println("Sorry, we are unable to authenticate you. Please contact your system administrator.");

        return false;
    }

    public String email() { return email; }

    /**
     * Returns the headline for the login user interface.
     *
     * @return the headline for the login user interface
     */
    @Override
    public String headline() {
        return "Login";
    }
}
