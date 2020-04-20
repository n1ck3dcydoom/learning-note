package leetcode.easy;

import java.util.Scanner;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * <p>
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/16 上午12:08
 **/
public class _69_Sqrt {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            int x = scanner.nextInt();
            System.out.println(mySqrt(x));
        }
    }

    private static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        // 一个数的平方根一定小于这个数的一半
        //  0 1 2 3 4 5 6 7 8 9 10
        // 相当于我们从 0 - 5 这一半的区间内查找第一个平方大于x的数
        // 二分查找，比较mid × mid 和 x 的大小

        long low = 1;
        long high = x / 2;
        long mid = low + (high - low) / 2;

        System.out.println(Math.sqrt((double) x));
        while (low <= high) {
            // 当前mid的平方大于x，说明x的平方根在low - mid之间
            if (mid * mid > x) {
                high = mid - 1;
            } else if (mid * mid < x) {
                low = mid + 1;
            } else if (mid * mid == x) {
                return (int)mid;
            }
            mid = low + (high - low) / 2;
        }
        return (int)high;
    }
}