package leetcode.array.hard;

/**
 * Created by n!Ck
 * Date: 2022/10/27
 * Time: 00:18
 * Description:
 */

public class _902_NumbersAtMostNGivenDigitSet {

    public static void main(String[] args) {
        System.out.println(atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 100));
        System.out.println(atMostNGivenDigitSet(new String[]{"3", "4", "8"}, 4));
        System.out.println(atMostNGivenDigitSet(new String[]{"7"}, 8));
    }

    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int p = s.length();
        int q = digits.length;

        // 令 k = digits.length
        // 如果 n 是一个 m 位数,那么使用 digits 组成的 [m-1, m-2, m-3, ... 1] 位数一定是小于 n 的
        // 位数小于 n 的数一共有 k + k^2 + k^3 + ... + k^(m-1)

        int res = 0;
        for (int i = 1; i <= p - 1; i++) {
            res += Math.pow(q, i);
        }

        // 例如 digits = [1,2,3,5] n = 5432, 考虑所有能够组成的 m = 4 位数当中比 n = 5432 小的
        // START: 从高位往低位开始每位 i ,查找每个 digits 的元素 d[j]
        // d[j]=1 < 当前位数 i=1 ,则后面 m-i=3 位可以填充任何数字 k^(m-i) = 4^3,例如 1*** < 5432
        // d[j]=2 < 当前位数 i=1 ,同理有 k^(m-1) = 4^3,例如 2*** < 5432
        // 同理 d[j]=3 < 当前位数 i=1,有 4^3,例如 3*** < 5 4 3 2
        // 当 d[j]=5 == 当前位数 i=1,则需要判断下一位 i=2, goto START

        // 从高到低遍历每一位
        for (int i = 0; i < p; i++) {
            // 是否需要判断下一位数
            boolean b = false;
            // 当前位上的数字
            int c = s.charAt(i) - '0';
            // 从小到大遍历所有可供选择的数字
            for (String digit : digits) {
                // 当前可选择的数字
                int num = Integer.parseInt(digit);
                // 可选择的数字小于当前位的数字,即 1*** < 5432
                if (c > num) {
                    // 当前位 i 选择填入 num,剩下 p-i 位,每位都有 q 种选择,即 q^(p-i)
                    // 因为 i 从 0 开始遍历,计算剩下的位数时需要-1
                    res += Math.pow(q, p - i - 1);
                } else {
                    // 如果相等则需要判断下一个位的数字,break 当前循环
                    if (c == num) {
                        b = true;
                        break;
                    }
                }
            }
            if (!b) {
                return res;
            }
        }
        // 若中途出现了 digits[i] > c 的情况, b == true 会提前返回
        // 若每次都是 digits[i] == c 的情况, b == false, 此时相当于选择的元素完全和 n 相等,需要再+1
        return ++res;
    }
}
