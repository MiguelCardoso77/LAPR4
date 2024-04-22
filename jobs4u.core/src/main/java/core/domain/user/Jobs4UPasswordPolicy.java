package core.domain.user;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.strings.util.StringPredicates;

import java.util.Random;

/**
 * Enforces that passwords must be at least 6 characters long and have at least
 * one digit and one capital letter.
 *
 * <p>
 * look into
 * https://documentation.cpanel.net/display/CKB/How+to+Determine+Password+Strength
 * for example rules of password strength
 *
 * @author Paulo Gandra de Sousa 24/05/2019
 */
public class Jobs4UPasswordPolicy implements PasswordPolicy {

    @Override
    public boolean isSatisfiedBy(final String rawPassword) {
        // sanity check
        if (StringPredicates.isNullOrEmpty(rawPassword)) {
            return false;
        }

        // at least 6 characters long
        if (rawPassword.length() < 8) {
            return false;
        }

        // at least one digit
        if (!StringPredicates.containsDigit(rawPassword)) {
            return false;
        }

        // at least one capital letter
        return StringPredicates.containsCapital(rawPassword);
    }

    public String passwordGenerator(String name) {
        String initials = name.substring(0, 1).toUpperCase() + name.substring(1, Math.min(name.length(), 4));

        Random random = new Random();
        int randomNumber;

        if (name.length() < 3) {
            randomNumber = 10000000 + random.nextInt(9000000);
        } else {
            randomNumber = 100000 + random.nextInt(90000);
        }

        return initials + randomNumber;
    }

    /**
     * Check how strong a password is. just for demo purposes.
     *
     * <p>
     * look into
     * https://documentation.cpanel.net/display/CKB/How+to+Determine+Password+Strength
     * for example rules of password strength
     *
     * @param rawPassword the string to check
     * @return how strong a password is
     */
    public PasswordStrength strength(final String rawPassword) {
        PasswordStrength passwordStrength = PasswordStrength.WEAK;

        if (rawPassword.length() >= 12 || (rawPassword.length() >= 8 && StringPredicates.containsAny(rawPassword, "$#!%&?"))) {
            passwordStrength = PasswordStrength.EXCELLENT;
        } else if (rawPassword.length() >= 8) {
            passwordStrength = PasswordStrength.GOOD;
        }

        return passwordStrength;
    }

    public enum PasswordStrength {
        WEAK, GOOD, EXCELLENT,
    }
}
