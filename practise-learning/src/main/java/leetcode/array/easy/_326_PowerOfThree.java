package leetcode.array.easy;

public class _326_PowerOfThree {

    public static void main(String[] args) {
        System.out.println("4" + isPowerOfThree1(4));
        //        System.out.println("27" + isPowerOfThree(27));
        //        System.out.println("0" + isPowerOfThree(0));
        //        System.out.println("9" + isPowerOfThree(9));
        //        System.out.println("45" + isPowerOfThree(45));
    }

    private static int max3pow = 0;

    static {
        int max = Integer.MAX_VALUE;
        long x = 1;
        while (x <= max) {
            max3pow = Math.max(max3pow, (int) x);
            x *= 3;
        }
    }

    public static boolean isPowerOfThree1(int n) {
        if (n <= 0) {
            return false;
        }
        // 不使用循环，如果x是3的幂次，那么x/3也是3的幂次
        // int类型最大的3的幂次
        return max3pow % n == 0;
    }

    public static boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n % 3 == 0) {
            return true;
        }
        // 试除法，n=n/3，这个过程中，如果n能够被3整除，则返回true
        // 否则一致除到n<3，判断是否等于1，如果等于1则整除，否则false
        while (n > 3) {
            n = n / 3;
        }
        return n == 1;
    }
}