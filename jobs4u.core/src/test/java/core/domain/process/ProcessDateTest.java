package core.domain.process;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessDateTest {

    @Test
    void processDateConstructor_ValidParameters_CreatesInstance() {
        Calendar date = Calendar.getInstance();
        ProcessDate processDate = new ProcessDate(date);
        assertNotNull(processDate);
    }

    @Test
    void processDateEquals_SameDate_ReturnsTrue() {
        Calendar date = Calendar.getInstance();
        ProcessDate processDate1 = new ProcessDate(date);
        ProcessDate processDate2 = new ProcessDate(date);
        assertTrue(processDate1.equals(processDate2));
    }

    @Test
    void processDateEquals_DifferentDates_ReturnsFalse() {
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, 1); // Add one day to make the dates different
        ProcessDate processDate1 = new ProcessDate(date1);
        ProcessDate processDate2 = new ProcessDate(date2);
        assertFalse(processDate1.equals(processDate2));
    }

    @Test
    void processDateToString_ValidDate_ReturnsDateString() {
        Calendar date = Calendar.getInstance();
        ProcessDate processDate = new ProcessDate(date);
        assertEquals(date.toString(), processDate.toString());
    }
}
