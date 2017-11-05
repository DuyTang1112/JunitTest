package money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
    Currency SEK, DKK;
    Bank SweBank, Nordea, DanskeBank;

    @Before
    public void setUp() throws Exception {
        DKK = new Currency("DKK", 0.20);
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        Nordea = new Bank("Nordea", SEK);
        DanskeBank = new Bank("DanskeBank", DKK);
        SweBank.openAccount("Ulrika");
        SweBank.openAccount("Bob");
        Nordea.openAccount("Bob");
        DanskeBank.openAccount("Gertrud");
    }

    @Test
    public void testGetName() {
        assertEquals("SweBank",SweBank.getName());
        assertEquals("Nordea",Nordea.getName());
        assertEquals("DanskeBank",DanskeBank.getName());
    }

    @Test
    public void testGetCurrency() {

        assertEquals(DKK,DanskeBank.getCurrency());
        assertEquals(SEK,Nordea.getCurrency());
        assertEquals(SEK,SweBank.getCurrency());
    }

    @Test
    public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
        SweBank.openAccount("Bill");
        //SweBank.openAccount("Bob");
        Nordea.openAccount("Slim Shady");
        DanskeBank.openAccount("Lady Gaga");
    }

    @Test
    public void testDeposit() throws AccountDoesNotExistException {
        SweBank.deposit("Bob",new Money(10000,SEK));
        assertEquals(10000,(long)SweBank.getBalance("Bob"));
        Nordea.deposit("Bob",new Money(25000,SEK));
        assertEquals(25000,(long)Nordea.getBalance("Bob"));
        DanskeBank.deposit("Gertrud",new Money(25000,DKK));
        assertEquals(25000,(long)DanskeBank.getBalance("Gertrud"));
    }

    @Test
    public void testWithdraw() throws AccountDoesNotExistException {
        SweBank.deposit("Bob",new Money(10000,SEK));
        SweBank.withdraw("Bob",new Money(500,SEK));
        assertEquals(10000-500,(long)SweBank.getBalance("Bob"));//test failed
        Nordea.deposit("Bob",new Money(25000,SEK));
        Nordea.withdraw("Bob",new Money(20000,SEK));
        assertEquals(25000-20000,(long)Nordea.getBalance("Bob"));
        DanskeBank.deposit("Gertrud",new Money(25000,DKK));
        DanskeBank.withdraw("Gertrud",new Money(25000,DKK));
        assertEquals(0,(long)DanskeBank.getBalance("Gertrud"));
    }

    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        assertEquals(0,(long)SweBank.getBalance("Bob"));
        Nordea.deposit("Bob",new Money(25000,SEK));
        assertEquals(25000,(long)Nordea.getBalance("Bob"));
    }

    @Test
    public void testTransfer() throws AccountDoesNotExistException {
        SweBank.deposit("Bob",new Money(10000,SEK));
        SweBank.transfer("Bob",Nordea,"Bob",new Money(2000,SEK));
        assertEquals(2000,(long)Nordea.getBalance("Bob"));
        assertEquals(8000,(long)SweBank.getBalance("Bob"));
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        SweBank.deposit("Bob",new Money(10000,SEK));
        SweBank.addTimedPayment("Bob","1",2,4,new Money(1000,SEK),Nordea,"Bob");
        for (int i=0;i<6;i++){
            SweBank.tick();
        }
        assertEquals(10000-2*1000,(long)SweBank.getBalance("Bob"));
        Nordea.addTimedPayment("Bob","1",1,2,new Money(200,SEK),SweBank,"Bob");
        Nordea.tick();
        Nordea.tick();
        Nordea.tick();
        assertEquals(8000+400,(long)SweBank.getBalance("Bob"));
    }
}
