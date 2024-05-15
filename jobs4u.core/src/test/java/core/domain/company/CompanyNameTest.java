package core.domain.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyNameTest {

    @Test
    void constructor_WithValidDesignation_CreatesCompanyName() {
        String designation = "Test Company";

        CompanyName companyName = new CompanyName(designation);

        assertEquals(designation, companyName.designation());
    }

    @Test
    void constructor_WithNullDesignation_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(null));
    }

    @Test
    void constructor_WithEmptyDesignation_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(""));
    }

    @Test
    void equals_IdenticalCompanyNames_ReturnsTrue() {
        CompanyName companyName1 = new CompanyName("Test");
        CompanyName companyName2 = new CompanyName("Test");

        assertEquals(companyName1, companyName2);
    }

    @Test
    void equals_DifferentCompanyNames_ReturnsFalse() {
        CompanyName companyName1 = new CompanyName("Test1");
        CompanyName companyName2 = new CompanyName("Test2");

        assertNotEquals(companyName1, companyName2);
    }

    @Test
    void hashCode_IdenticalCompanyNames_HashCodesAreEqual() {
        CompanyName companyName1 = new CompanyName("Test");
        CompanyName companyName2 = new CompanyName("Test");

        assertEquals(companyName1.hashCode(), companyName2.hashCode());
    }

    @Test
    void compareTo_SameCompanyNames_ReturnsZero() {
        CompanyName companyName1 = new CompanyName("Test");
        CompanyName companyName2 = new CompanyName("Test");

        assertEquals(0, companyName1.compareTo(companyName2));
    }

    @Test
    void compareTo_DifferentCompanyNames_ReturnsNonZero() {
        CompanyName companyName1 = new CompanyName("Test1");
        CompanyName companyName2 = new CompanyName("Test2");

        assertNotEquals(0, companyName1.compareTo(companyName2));
    }
}