package money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency SEK, DKK, NOK, EUR;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
    }

    @Test
    public void testGetName() {
        assertEquals("SEK",SEK.getName());
        assertEquals("DKK",DKK.getName());
        assertEquals("EUR",EUR.getName());
    }

    @Test
    public void testGetRate() {
        assertEquals(.15,SEK.getRate(),.0000000001);
        assertEquals(.20,DKK.getRate(),.0000000001);
        assertEquals(1.5,EUR.getRate(),.0000000001);
    }

    @Test
    public void testSetRate() {
        SEK.setRate(1.2);
        DKK.setRate(1.8);
        EUR.setRate(12.0);
        assertEquals(1.2,SEK.getRate(),.0000000001);
        assertEquals(1.8,DKK.getRate(),.0000000001);
        assertEquals(12.0,EUR.getRate(),.0000000001);
    }

    @Test
    public void testGlobalValue() {

        assertEquals(2250,(long)SEK.universalValue(15000));
        assertEquals(3000,(long)DKK.universalValue(15000));
        assertEquals(22500,(long)EUR.universalValue(15000));
    }

    @Test
    public void testValueInThisCurrency() {
        assertEquals(20000,(long)SEK.valueInThisCurrency(15000,DKK));
        assertEquals(112500,(long)DKK.valueInThisCurrency(15000,EUR));
        assertEquals(1500,(long)EUR.valueInThisCurrency(15000,SEK));
        //assertEquals(3000,(long)SEK.valueInThisCurrency(2000,EUR));
    }

}
