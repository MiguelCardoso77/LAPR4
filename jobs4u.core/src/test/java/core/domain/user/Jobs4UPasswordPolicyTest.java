package core.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Jobs4UPasswordPolicyTest {
    @Test
    void testIsSatisfiedBy_ValidPassword() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertTrue(passwordPolicy.isSatisfiedBy("StrongPassword1"));
    }

    @Test
    void testIsSatisfiedBy_TooShort() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertFalse(passwordPolicy.isSatisfiedBy("Pwd1"));
    }

    @Test
    void testIsSatisfiedBy_NoDigit() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertFalse(passwordPolicy.isSatisfiedBy("WeakPassword"));
    }

    @Test
    void testIsSatisfiedBy_NoCapitalLetter() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertFalse(passwordPolicy.isSatisfiedBy("weakpassword1"));
    }

    @Test
    void testPasswordGenerator() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        String generatedPassword = passwordPolicy.passwordGenerator("Guilherme");
        assertNotNull(generatedPassword);
        assertFalse(generatedPassword.isEmpty());
    }

    @Test
    void testStrength_InvalidPassword() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertEquals(Jobs4UPasswordPolicy.PasswordStrength.INVALID, passwordPolicy.strength("invpassword"));
    }

    @Test
    void testStrength_GoodPassword() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertEquals(Jobs4UPasswordPolicy.PasswordStrength.GOOD, passwordPolicy.strength("GoodPassword1"));
    }

    @Test
    void testStrength_ExcellentPassword() {
        Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();
        assertEquals(Jobs4UPasswordPolicy.PasswordStrength.EXCELLENT, passwordPolicy.strength("Exce11entP@ssw0rd"));
    }

}