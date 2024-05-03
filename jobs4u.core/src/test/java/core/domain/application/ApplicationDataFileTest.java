package core.domain.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*

public class ApplicationDataFileTest {

    @Test
    void ApplicationDataFileConstructor_NullApplicationDataFile_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ApplicationFiles(null));
    }

    @Test
    void ApplicationDataFileConstructor_EmptyApplicationDataFile_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ApplicationFiles(""));
    }

    @Test
    void ApplicationDataFileConstructor_ValidApplicationDataFile_CreatesInstance() {
        ApplicationDataFile applicationDataFile = new ApplicationDataFile(" 3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789");
        assertNotNull(applicationDataFile);
    }

    @Test
    void equals_SameInstance_ReturnsTrue() {
        ApplicationDataFile applicationDataFile = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        assertTrue(applicationDataFile.equals(applicationDataFile));
    }

    @Test
    void equals_EqualApplicationDataFilees_ReturnsTrue() {
        ApplicationDataFile applicationDataFile1 = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        ApplicationDataFile applicationDataFile2 = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        assertTrue(applicationDataFile1.equals(applicationDataFile2));
    }

    @Test
    void equals_DifferentApplicationDataFilees_ReturnsFalse() {
        ApplicationDataFile applicationDataFile1 = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        ApplicationDataFile applicationDataFile2 = new ApplicationDataFile("3 , candidateTwo@gmail.com ,  CandidateTwo ExampleTwo , 987654321");
        assertFalse(applicationDataFile1.equals(applicationDataFile2));
    }

    @Test
    void equals_NullApplicationDataFile_ReturnsFalse() {
        ApplicationDataFile applicationDataFile = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        assertFalse(applicationDataFile.equals(null));
    }

    @Test
    void hashCode_SameApplicationDataFilees_ReturnsSameHashCode() {
        ApplicationDataFile applicationDataFile1 = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        ApplicationDataFile applicationDataFile2 = new ApplicationDataFile("3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y");
        assertEquals(applicationDataFile1.hashCode(), applicationDataFile2.hashCode());
    }

    @Test
    void toString_ValidApplicationDataFile_ReturnsApplicationDataFileString() {
        String applicationDataFileString = "3 , candidateOne@gmail.com ,  CandidateOne ExampleOne , 123456789y";
        ApplicationDataFile applicationDataFile = new ApplicationDataFile(applicationDataFileString);
        assertEquals(applicationDataFileString, applicationDataFile.toString());
    }

}
        */
