package core.domain.user;

import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Jobs4URolesTest {
    @Test
    public void testRolesDefined() {
        assertEquals(Role.valueOf("ADMIN"), Jobs4URoles.ADMIN);
        assertEquals(Role.valueOf("BOOTSTRAP"), Jobs4URoles.BOOTSTRAP);
        assertEquals(Role.valueOf("CANDIDATE"), Jobs4URoles.CANDIDATE);
        assertEquals(Role.valueOf("CUSTOMER"), Jobs4URoles.CUSTOMER);
        assertEquals(Role.valueOf("CUSTOMER_MANAGER"), Jobs4URoles.CUSTOMER_MANAGER);
        assertEquals(Role.valueOf("LANGUAGE_ENGINEER"), Jobs4URoles.LANGUAGE_ENGINEER);
        assertEquals(Role.valueOf("OPERATOR"), Jobs4URoles.OPERATOR);
    }

    @Test
    public void testAllValues() {
        Role[] allRoles = Jobs4URoles.allValues();
        assertEquals(7, allRoles.length); // Ensure all roles are present
        assertTrue(containsRole(allRoles, Jobs4URoles.ADMIN));
        assertTrue(containsRole(allRoles, Jobs4URoles.BOOTSTRAP));
        assertTrue(containsRole(allRoles, Jobs4URoles.CANDIDATE));
        assertTrue(containsRole(allRoles, Jobs4URoles.CUSTOMER));
        assertTrue(containsRole(allRoles, Jobs4URoles.CUSTOMER_MANAGER));
        assertTrue(containsRole(allRoles, Jobs4URoles.LANGUAGE_ENGINEER));
        assertTrue(containsRole(allRoles, Jobs4URoles.OPERATOR));
    }

    private boolean containsRole(Role[] roles, Role role) {
        for (Role r : roles) {
            if (r.equals(role)) {
                return true;
            }
        }
        return false;
    }

}