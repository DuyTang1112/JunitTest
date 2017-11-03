package money;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {

        assertEquals(0,(long)SEK0.getAmount());
        assertEquals(10000,(long) SEK100.getAmount());
        assertEquals(20000,(long) SEK200.getAmount());
        assertEquals(0,(long)EUR0.getAmount());
        assertEquals(1000,(long)EUR10.getAmount());
        assertEquals(2000,(long)EUR20.getAmount());
        assertEquals(-10000,(long)SEKn100.getAmount());
    }

    @Test
    public void testGetCurrency() {

        assertEquals(SEK,SEK0.getCurrency());
        assertEquals(SEK,SEK100.getCurrency());
        assertEquals(SEK,SEK200.getCurrency());
        assertEquals(SEK,SEKn100.getCurrency());
        assertEquals(EUR,EUR0.getCurrency());
        assertEquals(EUR,EUR10.getCurrency());
        assertEquals(EUR,EUR20.getCurrency());
    }

    @Test
    public void testToString() {

        assertEquals("0.0 SEK",SEK0.toString());
        assertEquals("100.0 SEK",SEK100.toString());
        assertEquals("200.0 SEK",SEK200.toString());
        assertEquals("0.0 EUR",EUR0.toString());
        assertEquals("10.0 EUR",EUR10.toString());
        assertEquals("20.0 EUR",EUR20.toString());
        assertEquals("-100.0 SEK",SEKn100.toString());

    }

    @Test
    public void testGlobalValue() {
        assertEquals(1500,(long)SEK100.universalValue());
        assertEquals(0,(long)SEK0.universalValue());
        assertEquals(3000,(long)SEK200.universalValue());
        assertEquals(-1500,(long)SEKn100.universalValue());
        assertEquals(0,(long)EUR0.universalValue());
        assertEquals(3000,(long)EUR20.universalValue());
        assertEquals(1500,(long)EUR10.universalValue());
    }

    @Test
    public void testEqualsMoney() {
        String equal="Equal amount";
        String not_equal="Not equal amount";
        assertTrue(not_equal,SEK0.equals(EUR0));
        assertTrue(not_equal,SEK100.equals(EUR10));

        assertTrue(not_equal,SEK200.equals(EUR20));
        assertFalse(equal,SEK100.equals(EUR20));
    }

    @Test
    public void testAdd() {
        assertEquals((long)new Money(30000,SEK).getAmount(),(long)SEK100.add(SEK200).getAmount());
        assertEquals(6000,(long)SEK200.add(EUR20).universalValue());
        assertEquals(0,(long)SEKn100.add(EUR10).universalValue());
    }

    @Test
    public void testSub() {
        assertEquals(1500,(long)SEK200.sub(SEK100).universalValue());
        assertEquals(-1500,(long) EUR10.sub(SEK200).universalValue());
        assertEquals(0,(long)SEK0.sub(EUR0).universalValue());
    }

    @Test
    public void testIsZero() {
        String is_zero="Is Zero", not_zero="Not zero";
        assertTrue(not_zero,SEK0.isZero());
        assertFalse(is_zero,SEK200.isZero());
    }

    @Test
    public void testNegate() {

        assertEquals(-20000,(long)SEK200.negate().getAmount());
        assertEquals(1500,(long)SEKn100.negate().universalValue());
        assertEquals(0,(long)SEK0.negate().getAmount());
    }

    @Test
    public void testCompareTo() {

        assertEquals(0,(long)SEK100.compareTo(EUR10));
        assertEquals(0,(long)SEK200.compareTo(EUR20));
        assertEquals(1,(long) SEK200.compareTo(EUR10));
        assertEquals(-1,(long)SEKn100.compareTo(SEK0));
    }
}
