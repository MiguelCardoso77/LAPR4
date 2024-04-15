package domain;

import authentication.Jobs4uPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

@Utility
public class UserBuilderHelper {
    private UserBuilderHelper() {
        // ensure utility
    }

    public static SystemUserBuilder builder() {
        return new SystemUserBuilder(new Jobs4uPasswordPolicy(), new PlainTextEncoder());
    }

}