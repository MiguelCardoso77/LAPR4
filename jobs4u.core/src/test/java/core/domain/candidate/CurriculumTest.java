package core.domain.candidate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurriculumTest {
    @Test
    public void testCurriculumCreation_ValidPath() {
        String path = "path/to/curriculum.pdf";

        Curriculum curriculum = new Curriculum(path);

        assertEquals(path, curriculum.curriculumPath());
    }

    @Test
    public void testCurriculumCreation_NullPath() {
        assertThrows(IllegalArgumentException.class, () -> new Curriculum(null));
    }

    @Test
    public void testCurriculumCreation_EmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> new Curriculum(""));
    }

    @Test
    public void testCurriculumEquality_SamePath() {
        String path = "path/to/curriculum.pdf";
        Curriculum curriculum1 = new Curriculum(path);
        Curriculum curriculum2 = new Curriculum(path);

        assertEquals(curriculum1, curriculum2);
    }

    @Test
    public void testCurriculumEquality_DifferentPath() {
        Curriculum curriculum1 = new Curriculum("path1/to/curriculum.pdf");
        Curriculum curriculum2 = new Curriculum("path2/to/curriculum.pdf");

        assertNotEquals(curriculum1, curriculum2);
    }

    @Test
    public void testCurriculumHashCode_SamePath() {
        String path = "path/to/curriculum.pdf";
        Curriculum curriculum1 = new Curriculum(path);
        Curriculum curriculum2 = new Curriculum(path);

        assertEquals(curriculum1.hashCode(), curriculum2.hashCode());
    }

    @Test
    public void testCurriculumHashCode_DifferentPath() {
        Curriculum curriculum1 = new Curriculum("path1/to/curriculum.pdf");
        Curriculum curriculum2 = new Curriculum("path2/to/curriculum.pdf");

        assertNotEquals(curriculum1.hashCode(), curriculum2.hashCode());
    }

    @Test
    public void testCurriculumToString() {
        String path = "path/to/curriculum.pdf";
        Curriculum curriculum = new Curriculum(path);

        assertEquals(path, curriculum.toString());
    }

}