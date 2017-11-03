package introduction;

/**
 * Simple class used to calculate the fibonacci of a input n
 */
public class Fibonacci {
    public int fib(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return (fib(n - 1) + fib(n - 2)) ;
        }
    }
}
