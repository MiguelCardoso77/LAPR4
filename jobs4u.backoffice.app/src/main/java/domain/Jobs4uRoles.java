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
     * Get available role types for cafeteria employees.
     *
     * @return employee roles
     */
    public static Role[] nonUserValues() {
        return new Role[] { ADMIN, CANDIDATE, CUSTOMER, CUSTOMER_MANAGER, LANGUAGE_ENGINEER, OPERATOR };
    }

    /**
     * Checks if a role is one of the employee roles.
     *
     * @param role
     * @return {@code true} if a role is one of the employee roles
     */
    public boolean isCollaborator(final Role role) {
        return role != JOBS4U_USER;
    }
}
