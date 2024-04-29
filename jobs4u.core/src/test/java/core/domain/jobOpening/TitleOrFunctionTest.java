package core.domain.jobOpening;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TitleOrFunctionTest {

    @Test
    void testEquals_SameTitleOrFunction_ShouldReturnTrue() {
        // Arrange
        TitleOrFunction title1 = TitleOrFunction.valueOf("Software Engineer");
        TitleOrFunction title2 = TitleOrFunction.valueOf("Software Engineer");

        // Act & Assert
        assertEquals(title1, title2);
    }

    @Test
    void testEquals_DifferentTitleOrFunction_ShouldReturnFalse() {
        // Arrange
        TitleOrFunction title1 = TitleOrFunction.valueOf("Product Manager");
        TitleOrFunction title2 = TitleOrFunction.valueOf("Data Scientist");

        // Act & Assert
        assertNotEquals(title1, title2);
    }

    @Test
    void testHashCode_SameTitleOrFunction_ShouldHaveEqualHashCodes() {
        // Arrange
        TitleOrFunction title1 = TitleOrFunction.valueOf("UI Designer");
        TitleOrFunction title2 = TitleOrFunction.valueOf("UI Designer");

        // Act & Assert
        assertEquals(title1.hashCode(), title2.hashCode());
    }

    @Test
    void testHashCode_DifferentTitleOrFunction_ShouldHaveDifferentHashCodes() {
        // Arrange
        TitleOrFunction title1 = TitleOrFunction.valueOf("Marketing Specialist");
        TitleOrFunction title2 = TitleOrFunction.valueOf("Content Writer");

        // Act & Assert
        assertNotEquals(title1.hashCode(), title2.hashCode());
    }

    @Test
    void testToString_ShouldReturnTitleOrFunctionString() {
        // Arrange
        String titleString = "Software Architect";
        TitleOrFunction title = TitleOrFunction.valueOf(titleString);

        // Act & Assert
        assertEquals(titleString, title.toString());
    }
}
