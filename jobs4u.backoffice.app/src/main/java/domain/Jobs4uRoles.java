package domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

public class Jobs4uRoles {
    /**
     * User.
     */
    public static final Role JOBS4U_USER = Role.valueOf("JOBS4U_USER");

    /**
     * Role for application users.
     */
    public static final Role ADMIN = Role.valueOf("ADMIN");
    public static final Role CANDIDATE = Role.valueOf("CANDIDATE");
    public static final Role CUSTOMER = Role.valueOf("COSTUMER");
    public static final Role CUSTOMER_MANAGER = Role.valueOf("COSTUMER_MANAGER");
    public static final Role LANGUAGE_ENGINEER = Role.valueOf("LANGUAGE_ENGINEER");
    public static final Role OPERATOR = Role.valueOf("OPERATOR");

    /**
     * Get available role types for the application.
     *
     * @return an array with the available role types
     */
    public static Role[] getAllAssignableRoles() {
        return new Role[]{ADMIN, CANDIDATE, CUSTOMER, CUSTOMER_MANAGER, LANGUAGE_ENGINEER, OPERATOR};
    }
}
