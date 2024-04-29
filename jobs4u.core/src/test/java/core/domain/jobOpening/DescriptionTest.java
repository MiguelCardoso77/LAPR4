package core.domain.jobOpening;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DescriptionTest {

    @Test
    void descriptionConstructor_ValidDescription_CreatesInstance() {
        Description description = new Description("This is a job description");
        assertNotNull(description);
    }

    @Test
    void descriptionEquals_SameDescription_ReturnsTrue() {
        Description description1 = new Description("This is a job description");
        Description description2 = new Description("This is a job description");
        assertTrue(description1.equals(description2));
    }

    @Test
    void descriptionEquals_DifferentDescriptions_ReturnsFalse() {
        Description description1 = new Description("This is a job description");
        Description description2 = new Description("Another job description");
        assertFalse(description1.equals(description2));
    }

    @Test
    void descriptionToString_ValidDescription_ReturnsDescriptionString() {
        String descriptionString = "This is a job description";
        Description description = new Description(descriptionString);
        assertEquals(descriptionString, description.toString());
    }
}
