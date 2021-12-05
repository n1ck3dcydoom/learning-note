package leetcode.math.medium.exponentiation;

/**
 * Created by n!Ck
 * Date: 2021/12/23
 * Time: 22:58
 * Description:
 */

public class _50_Pow {

    public static void main(String[] args) {
        System.out.println(myPow(2.0D, 10));
        System.out.println(myPow(2.1D, 3));
        System.out.println(myPow(2.0D, -2));
        System.out.println(myPow(2.0D, Integer.MIN_VALUE));
    }

    public static double myPow(double x, int n) {
        return n >= 0 ? qpow(x, n) : 1 / qpow(x, -(long) n);
    }

    private static double qpow(double x, long n) {
        double res = 1D;
        while (n > 0) {
            if ((n & 1) == 1) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
}
