package core.domain.jobOpening;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VacanciesNumberTest {

    @Test
    void constructor_negativeNumber_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new VacanciesNumber(-1));
    }

    @Test
    void constructor_validNumber_createsInstance() {
        assertEquals(5, new VacanciesNumber(5).number());
    }

    @Test
    void valueOf_providesValidInstance() {
        VacanciesNumber vn = VacanciesNumber.valueOf(10);
        assertNotNull(vn);
        assertEquals(10, vn.number());
    }

    @Test
    void equals_sameNumber_returnsTrue() {
        VacanciesNumber vn1 = new VacanciesNumber(10);
        VacanciesNumber vn2 = new VacanciesNumber(10);
        assertEquals(vn1, vn2);
    }

    @Test
    void equals_differentNumber_returnsFalse() {
        VacanciesNumber vn1 = new VacanciesNumber(10);
        VacanciesNumber vn2 = new VacanciesNumber(20);
        assertNotEquals(vn1, vn2);
    }

    @Test
    void compareTo_lessThan_returnsNegative() {
        VacanciesNumber vn1 = new VacanciesNumber(5);
        VacanciesNumber vn2 = new VacanciesNumber(10);
        assertTrue(vn1.compareTo(vn2) < 0);
    }

    @Test
    void compareTo_greaterThan_returnsPositive() {
        VacanciesNumber vn1 = new VacanciesNumber(15);
        VacanciesNumber vn2 = new VacanciesNumber(10);
        assertTrue(vn1.compareTo(vn2) > 0);
    }

    @Test
    void compareTo_equalTo_returnsZero() {
        VacanciesNumber vn1 = new VacanciesNumber(10);
        VacanciesNumber vn2 = new VacanciesNumber(10);
        assertEquals(0, vn1.compareTo(vn2));
    }
}
