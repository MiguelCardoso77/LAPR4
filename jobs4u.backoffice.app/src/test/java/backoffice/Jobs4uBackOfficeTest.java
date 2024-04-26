package backoffice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Jobs4uBackOfficeTest {

    @Test
    public void testAppTitle() {
        Assertions.assertEquals("Jobs4u Back Office", new Jobs4uBackOffice().appTitle());
    }

    @Test
    public void testAppGoodbye() {
        Assertions.assertEquals("Jobs4u Back Office", new Jobs4uBackOffice().appGoodbye());
    }

}