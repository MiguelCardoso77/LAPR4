package core.domain.interview;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    public void testConstructorAndGetters() {
        Result result = new Result("Accepted");
        assertEquals("Accepted", result.toString());
    }

    @Test
    public void testEquals() {
        Result result1 = new Result("Accepted");
        Result result2 = new Result("Accepted");
        Result result3 = new Result("Rejected");

        assertEquals(result1, result2);
        assertNotEquals(result1, result3);
    }

    @Test
    public void testHashCode() {
        Result result1 = new Result("Accepted");
        Result result2 = new Result("Accepted");

        assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void testToString() {
        Result result = new Result("Rejected");
        assertEquals("Rejected", result.toString());
    }
}
