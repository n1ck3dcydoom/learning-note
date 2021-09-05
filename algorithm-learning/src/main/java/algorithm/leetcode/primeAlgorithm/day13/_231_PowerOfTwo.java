package algorithm.leetcode.primeAlgorithm.day13;

public class _231_PowerOfTwo {

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MIN_VALUE + (Integer.MIN_VALUE - 1));

        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(4));
    }

    public static boolean isPowerOfTwo(int n) {
        // 2的幂次有个特点，即二进制表达式上有且只有一个1
        // 位运算n&(n-1)可以把n的最后一个1置0
        // 经过若干次上述运算后，n最终结果为0

        // 2的幂次一定是正整数，负数不可能是2的幂次方
        // 而且对于int类型的最小数Integer.MIN_VALUE，进行位运算n&(n-1)
        // 这里的n-1会导致溢出
        // int count = 0;
        // while (n > 0) {
        // n = n & (n - 1);
        // count++;
        // }
        // return count == 1;

        // 不使用循环
        return n > 0 && (n & (n - 1)) == 0;
    }
}
