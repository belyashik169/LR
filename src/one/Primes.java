package one;

public class Primes {
    public static void main(String[] args) {
        for (int n = 2; n < 100; n++)
            if (isPrime(n)==true) System.out.println(n);
    }
    public static boolean isPrime(int n) {
        for (int j = 2; j < n; j++)
            if (n % j == 0)  return false;
        return true;
    }
}
