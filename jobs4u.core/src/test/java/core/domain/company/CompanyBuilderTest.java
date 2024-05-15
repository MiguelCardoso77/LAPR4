package core.domain.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyBuilderTest {

    @Test
    void build_WithRequiredFields_ReturnsCompanyInstance() {
        CompanyBuilder builder = new CompanyBuilder();
        CompanyName companyName = new CompanyName("Example");
        int companyNumber = 1;

        Company company = builder.withCompanyName(companyName)
                .withCompanyNumber(companyNumber)
                .build();

        assertEquals(companyName, company.companyName());
        assertEquals(companyNumber, company.identity());
    }

    @Test
    void build_WithoutCompanyName_ThrowsNullPointerException() {
        CompanyBuilder builder = new CompanyBuilder();
        int companyNumber = 1;

        assertThrows(IllegalArgumentException.class, () -> builder.withCompanyNumber(companyNumber).build());
    }

    @Test
    void build_WithoutCompanyNameString_ThrowsNullPointerException() {
        CompanyBuilder builder = new CompanyBuilder();
        String companyName = null;
        int companyNumber = 1;

        assertThrows(IllegalArgumentException.class, () -> builder.withCompanyName(companyName).withCompanyNumber(companyNumber).build());
    }
}