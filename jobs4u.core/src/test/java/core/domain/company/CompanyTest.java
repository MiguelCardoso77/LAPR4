package core.domain.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void equals_IdenticalCompanies_ReturnsTrue() {
        CompanyName companyName = new CompanyName("Example");
        Company company1 = new Company(companyName, 1);
        Company company2 = new Company(companyName, 1);

        assertEquals(company1, company2);
    }

    @Test
    void equals_DifferentCompanies_ReturnsFalse() {
        CompanyName companyName1 = new CompanyName("Example");
        CompanyName companyName2 = new CompanyName("Example1");
        Company company1 = new Company(companyName1, 1);
        Company company2 = new Company(companyName2, 2);

        assertNotEquals(company1, company2);
    }

    @Test
    void hashCode_IdenticalCompanies_HashCodesAreEqual() {
        CompanyName companyName = new CompanyName("Example");
        Company company1 = new Company(companyName, 1);
        Company company2 = new Company(companyName, 1);

        assertEquals(company1.hashCode(), company2.hashCode());
    }

    @Test
    void companyName_ReturnsExpectedCompanyName() {
        CompanyName companyName = new CompanyName("Example");
        Company company = new Company(companyName, 1);

        assertEquals(companyName, company.companyName());
    }

    @Test
    void sameAs_IdenticalCompanies_ReturnsTrue() {
        CompanyName companyName = new CompanyName("Example");
        Company company1 = new Company(companyName, 1);
        Company company2 = new Company(companyName, 1);

        assertEquals(company1.sameAs(company2), company1.equals(company2));
    }

    @Test
    void sameAs_DifferentCompanies_ReturnsFalse() {
        CompanyName companyName1 = new CompanyName("Example");
        CompanyName companyName2 = new CompanyName("Example1");
        Company company1 = new Company(companyName1, 1);
        Company company2 = new Company(companyName2, 2);

        assertFalse(company1.sameAs(company2));
    }

    @Test
    void identity_ReturnsExpectedCompanyNumber() {
        int companyNumber = 1;
        CompanyName companyName = new CompanyName("Example");
        Company company = new Company(companyName, companyNumber);

        assertEquals(companyNumber, company.identity());
    }

    @Test
    void toString_ReturnsExpectedStringRepresentation() {
        int companyNumber = 1;
        String companyNameString = "Company Name";
        CompanyName companyName = new CompanyName(companyNameString);
        Company company = new Company(companyName, companyNumber);
        String expected = "Company : companyNumber = 1, companyName = " + companyNameString;

        assertEquals(expected, company.toString());
    }
}