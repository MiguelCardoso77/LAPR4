package core.domain.jobRequirementsSpecification;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProgrammingLanguagesTest {

    @Test
    public void testToString() {
        assertEquals("Java", ProgrammingLanguages.JAVA.toString());
        assertEquals("JavaScript", ProgrammingLanguages.JAVASCRIPT.toString());
        assertEquals("Python", ProgrammingLanguages.PYTHON.toString());
        assertEquals("TypeScript", ProgrammingLanguages.TYPESCRIPT.toString());
        assertEquals("PHP", ProgrammingLanguages.PHP.toString());
        assertEquals("C#", ProgrammingLanguages.CHASH.toString());
    }
}
