package core.domain.user;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.strings.util.StringPredicates;

import java.util.Random;

/**
 * Enforces that passwords must be at least 8 characters long and have at least
 * one digit and one capital letter.
 *
 * <p>
 * This class implements the {@link eapli.framework.infrastructure.authz.application.PasswordPolicy}
 * interface and provides methods to check the strength of a password and generate a random password.
 * It also defines an enum {@link PasswordStrength} to represent the strength levels of passwords.
 * </p>
 *
 * @author Miguel Cardoso
 */
public class Jobs4UPasswordPolicy implements PasswordPolicy {

    /**
     * Checks if the given password meets the criteria for a strong password.
     *
     * @param rawPassword the password to be checked
     * @return true if the password is strong, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(final String rawPassword) {
        // sanity check
        if (StringPredicates.isNullOrEmpty(rawPassword)) {
            return false;
        }

        // at least 8 characters long
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

    /**
     * Generates a random password based on the given name.
     *
     * @param name the name to be used for generating the password
     * @return a randomly generated password
     */
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
     * Evaluates the strength of a password.
     *
     * @param rawPassword the password to be evaluated
     * @return the strength level of the password
     */
    public PasswordStrength strength(final String rawPassword) {
        PasswordStrength passwordStrength = PasswordStrength.WEAK;

        if (!isSatisfiedBy(rawPassword)) {
            return PasswordStrength.INVALID;
        }

        if (rawPassword.length() >= 14 || (rawPassword.length() >= 10 && StringPredicates.containsAny(rawPassword, "$#!%&?"))) {
            passwordStrength = PasswordStrength.EXCELLENT;
        } else if (rawPassword.length() >= 10) {
            passwordStrength = PasswordStrength.GOOD;
        }

        return passwordStrength;
    }

    /**
     * Enum representing the strength levels of passwords.
     */
    public enum PasswordStrength {
        INVALID, WEAK, GOOD, EXCELLENT,
    }
}
