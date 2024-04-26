package core.domain.candidate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelephoneNumberTest {

    @Test
    public void testTelephoneNumberCreation_ValidNumber() {
        String phoneNumber = "123456789";

        TelephoneNumber telephoneNumber = new TelephoneNumber(phoneNumber);

        assertEquals(phoneNumber, telephoneNumber.toString());
    }

    @Test
    public void testTelephoneNumberCreation_NullNumber() {
        assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber(null));
    }

    @Test
    public void testTelephoneNumberCreation_EmptyNumber() {
        assertThrows(IllegalArgumentException.class, () -> new TelephoneNumber(""));
    }

    @Test
    public void testTelephoneNumberEquality_SameNumber() {
        String phoneNumber = "123456789";
        TelephoneNumber telephoneNumber1 = new TelephoneNumber(phoneNumber);
        TelephoneNumber telephoneNumber2 = new TelephoneNumber(phoneNumber);

        assertEquals(telephoneNumber1, telephoneNumber2);
    }

    @Test
    public void testTelephoneNumberEquality_DifferentNumber() {
        TelephoneNumber telephoneNumber1 = new TelephoneNumber("123456789");
        TelephoneNumber telephoneNumber2 = new TelephoneNumber("987654321");

        assertNotEquals(telephoneNumber1, telephoneNumber2);
    }

    @Test
    public void testTelephoneNumberHashCode_SameNumber() {
        String phoneNumber = "123456789";
        TelephoneNumber telephoneNumber1 = new TelephoneNumber(phoneNumber);
        TelephoneNumber telephoneNumber2 = new TelephoneNumber(phoneNumber);

        assertEquals(telephoneNumber1.hashCode(), telephoneNumber2.hashCode());
    }

    @Test
    public void testTelephoneNumberHashCode_DifferentNumber() {
        TelephoneNumber telephoneNumber1 = new TelephoneNumber("123456789");
        TelephoneNumber telephoneNumber2 = new TelephoneNumber("987654321");

        assertNotEquals(telephoneNumber1.hashCode(), telephoneNumber2.hashCode());
    }

    @Test
    public void testTelephoneNumberComparison_LessThan() {
        TelephoneNumber telephoneNumber1 = new TelephoneNumber("123456789");
        TelephoneNumber telephoneNumber2 = new TelephoneNumber("987654321");

        assertTrue(telephoneNumber1.compareTo(telephoneNumber2) < 0);
    }

    @Test
    public void testTelephoneNumberComparison_GreaterThan() {
        TelephoneNumber telephoneNumber1 = new TelephoneNumber("987654321");
        TelephoneNumber telephoneNumber2 = new TelephoneNumber("123456789");

        assertTrue(telephoneNumber1.compareTo(telephoneNumber2) > 0);
    }

    @Test
    public void testTelephoneNumberComparison_Equal() {
        TelephoneNumber telephoneNumber1 = new TelephoneNumber("123456789");
        TelephoneNumber telephoneNumber2 = new TelephoneNumber("123456789");

        assertEquals(0, telephoneNumber1.compareTo(telephoneNumber2));
    }

}