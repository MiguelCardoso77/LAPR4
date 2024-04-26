package core.domain.user;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class Jobs4URoles {
    public static final Role CLIENT_USER = Role.valueOf("CLIENT_USER");

    public static final Role ADMIN = Role.valueOf("ADMIN");
    public static final Role BOOTSTRAP = Role.valueOf("BOOTSTRAP");
    public static final Role CANDIDATE = Role.valueOf("CANDIDATE");
    public static final Role CUSTOMER = Role.valueOf("CUSTOMER");
    public static final Role CUSTOMER_MANAGER = Role.valueOf("CUSTOMER_MANAGER");
    public static final Role LANGUAGE_ENGINEER = Role.valueOf("LANGUAGE_ENGINEER");
    public static final Role OPERATOR = Role.valueOf("OPERATOR");

    public static Role[] allValues() {
        return new Role[] { ADMIN, BOOTSTRAP, CANDIDATE, CUSTOMER, CUSTOMER_MANAGER, LANGUAGE_ENGINEER, OPERATOR };
    }

}
