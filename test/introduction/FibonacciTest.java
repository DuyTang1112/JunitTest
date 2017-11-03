package introduction;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {

    @Test
    public void testFib() {
        Fibonacci tester = new Fibonacci();
        Assert.assertEquals("0", 0, tester.fib(0));
        Assert.assertEquals("1", 1, tester.fib(1));
        Assert.assertEquals("2", 1, tester.fib(2));
        Assert.assertEquals("3", 2, tester.fib(3));
        Assert.assertEquals("4", 3, tester.fib(4));
        Assert.assertEquals("5", 5, tester.fib(5));
        Assert.assertEquals("6", 8, tester.fib(6));
        Assert.assertEquals("7", 13, tester.fib(7));
    }
}
