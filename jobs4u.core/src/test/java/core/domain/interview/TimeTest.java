package core.domain.interview;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTest {

    @Test
    public void testConstructorAndGetters() {
        Time time = new Time(60);
        assertEquals(60, time.time());
    }

    @Test
    public void testEquals() {
        Time time1 = new Time(60);
        Time time2 = new Time(60);
        Time time3 = new Time(45);

        assertEquals(time1, time2);
        assertNotEquals(time1, time3);
    }

    @Test
    public void testHashCode() {
        Time time1 = new Time(90);
        Time time2 = new Time(90);

        assertEquals(time1.hashCode(), time2.hashCode());
    }

    @Test
    public void testToString() {
        Time time = new Time(120);
        assertEquals("Time = 120", time.toString());
    }
}
