package money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    Currency SEK, DKK;
    Bank Nordea;
    Bank DanskeBank;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        SweBank.openAccount("Alice");
        testAccount = new Account("Hans", SEK);
        testAccount.deposit(new Money(10000000, SEK));
        SweBank.deposit("Alice", new Money(1000000, SEK));
    }

    @Test
    public void testAddRemoveTimedPayment() {
        testAccount.addTimedPayment("1",2,3,new Money(10000,SEK),SweBank,"Alice");
        for (int i=0;i<5;i++){
            testAccount.tick();
            //the 3th tick will trigger a transfer, next=interval=2
            //after 5 ticks, 2 transfers will be completed
            //check the modified Account class
        }
        assertEquals(10000000- 2*10000,(long)testAccount.getBalance().getAmount());//failed
        try {
            assertEquals(1000000+2*10000,(long)SweBank.getBalance("Alice"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        testAccount.addTimedPayment("1",2,3,new Money(10000,SEK),SweBank,"Alice");
        assertTrue("Payment does not exist",testAccount.timedPaymentExists("1"));
        testAccount.removeTimedPayment("1");
        assertFalse("Payment does exist",testAccount.timedPaymentExists("1"));
    }

    @Test
    public void testAddWithdraw() {
        testAccount.withdraw(new Money(40000,SEK));
        assertEquals(10000000-40000,(long)testAccount.getBalance().getAmount());
        testAccount.deposit(new Money(40000,SEK));
        assertEquals(10000000,(long)testAccount.getBalance().getAmount());
    }

    @Test
    public void testGetBalance() {
        assertEquals(10000000,(long)testAccount.getBalance().getAmount());
    }
}
