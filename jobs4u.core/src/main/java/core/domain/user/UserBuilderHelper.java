package core.domain.user;

import core.domain.client.SignupRequestBuilder;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

/**
 *
 * @author Paulo Gandra de Sousa 27/05/2019
 *
 */
@Utility
public class UserBuilderHelper {
    private UserBuilderHelper() {
        // ensure utility
    }

    public static SystemUserBuilder builder() {
        return new SystemUserBuilder(new Jobs4UPasswordPolicy(), new PlainTextEncoder());
    }

    public static SignupRequestBuilder signupBuilder() {
        return new SignupRequestBuilder(new Jobs4UPasswordPolicy(), new PlainTextEncoder());
    }
}
