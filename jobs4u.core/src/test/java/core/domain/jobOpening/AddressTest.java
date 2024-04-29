package core.domain.jobOpening;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void addressConstructor_NullAddress_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null));
    }

    @Test
    void addressConstructor_EmptyAddress_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Address(""));
    }

    @Test
    void addressConstructor_ValidAddress_CreatesInstance() {
        Address address = new Address("123 Main St, City, Country");
        assertNotNull(address);
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        Address address = new Address("123 Main St, City, Country");
        assertTrue(address.equals(address));
    }

    @Test
    void equals_EqualAddresses_ReturnsTrue() {
        Address address1 = new Address("123 Main St, City, Country");
        Address address2 = new Address("123 Main St, City, Country");
        assertTrue(address1.equals(address2));
    }

    @Test
    void equals_DifferentAddresses_ReturnsFalse() {
        Address address1 = new Address("123 Main St, City, Country");
        Address address2 = new Address("456 Elm St, Town, Country");
        assertFalse(address1.equals(address2));
    }

    @Test
    void equals_NullAddress_ReturnsFalse() {
        Address address = new Address("123 Main St, City, Country");
        assertFalse(address.equals(null));
    }

    @Test
    void hashCode_SameAddresses_ReturnsSameHashCode() {
        Address address1 = new Address("123 Main St, City, Country");
        Address address2 = new Address("123 Main St, City, Country");
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    void toString_ValidAddress_ReturnsAddressString() {
        String addressString = "123 Main St, City, Country";
        Address address = new Address(addressString);
        assertEquals(addressString, address.toString());
    }
}
