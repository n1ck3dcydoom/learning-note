package leetcode.simulate.medium;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by n!Ck
 * Date: 2022/7/27
 * Time: 22:37
 * Description:
 */

public class _592_FractionAdditionAndSubtraction {

    public static void main(String[] args) {
        System.out.println(fractionAddition("-1/2+1/2"));
        System.out.println(fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fractionAddition("1/3-1/2"));
        System.out.println(fractionAddition("-1/4-4/5-1/4"));
    }

    public static String fractionAddition(String expression) {
        // 每个负数的前面都加一个+号，全部当做加法来处理
        String s = expression.replaceAll("-", "+-");
        // 有可能出现空串，即第一个分数是负数会变成+-x，此时split之后第一个元素是空串
        List<String> nums = Arrays.stream(s.split("\\+")).filter(str -> !"".equals(str)).collect(Collectors.toList());

        // 直接模拟计算
        String res = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            res = plus(res, nums.get(i));
        }
        return res;
    }

    private static String plus(String str1, String str2) {
        if (!str1.contains("/")) {
            str1 += "/1";
        }
        if (!str2.contains("/")) {
            str2 += "/1";
        }
        String[] split1 = str1.split("/");
        String[] split2 = str2.split("/");
        // 分子
        int u1 = Integer.parseInt(split1[0]);
        int u2 = Integer.parseInt(split2[0]);
        // 分母
        int d1 = Integer.parseInt(split1[1]);
        int d2 = Integer.parseInt(split2[1]);
        // 分母通分，分子相加减 u1/d1+u2/d2 = u1*d2/d1*d2 + u2*d1/d1*d2 = (u1*d2+u2*d1)/d1*d2
        int u = u1 * d2 + u2 * d1;
        int d = d1 * d2;
        if (u == 0) {
            return "0/1";
        }
        // 约分
        int gcd = gcd(Math.max(Math.abs(u), Math.abs(d)), Math.min(Math.abs(u), Math.abs(d)));
        return u / gcd + "/" + d / gcd;
    }

    // a 和 b 求最大公因数，假设 a >= b
    // ① 设 c = a % b
    // ② 令 a = b, b = c
    // 继续重复 ①② 操作，直到 b = 0，此时的 a 就是 a 和 b 的最大公因数
    // 567 % 405 = 162
    // 405 % 162 = 81
    // 162 % 81 = 0
    // 81 % 0 此时 b = 0，则 a = 81 就是原来 567 和 405 的最大公因数
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
