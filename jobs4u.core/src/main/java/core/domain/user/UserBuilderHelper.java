package core.domain.user;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

/**
 * A helper class for building User objects in the Jobs4U system.
 * This class provides a method to get a SystemUserBuilder configured with the Jobs4UPasswordPolicy and a PlainTextEncoder.
 * It is marked as a utility class, meaning it's not meant to be instantiated or subclassed.
 *
 * @author Miguel Cardoso
 */
@Utility
public class UserBuilderHelper {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private UserBuilderHelper() {
        // ensure utility
    }

    /**
     * Returns a SystemUserBuilder configured with the Jobs4UPasswordPolicy and a PlainTextEncoder.
     *
     * @return A SystemUserBuilder.
     */
    public static SystemUserBuilder builder() {
        return new SystemUserBuilder(new Jobs4UPasswordPolicy(), new PlainTextEncoder());
    }
}
