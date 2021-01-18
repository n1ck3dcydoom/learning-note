package leetcode.dp.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description leetcode
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * <p>
 * 示例:
 * <p>
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/18 19:58
 **/
public class _357_CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    private static int countNumbersWithUniqueDigits(int n) {
        // 9 8 7 6 5 4 3 2 1 0 最大的每位都不同的数字，已经超过int的表示范围了
        long max = 9876543210L;
        // 10的9次方    1000000000
        // 最大的不同位数9876543210
        // 10的10次方   10000000000

        // 第一步、定义dp[i]表示n=i时，每位都不同的数字个数，0<=n<=10，10以后的dp=dp[10]
        int[] dp = new int[11];

        // 第二步、初始值
        dp[0] = 1;
        dp[1] = 10;

        // 第三步、选择，即递推表达式
        // n=1时，[0,10) 一位数出有10个，0~1
        // n=2时，[0,100)两位数有：首位不能为0的一位数，10个
        //                        首位9个选择的二位数，9*9=81个
        // n=2时，有10+81=91个
        // n=3时，[0,1000)三位数有：已经计算好的两位数91个
        //                        首位不能为0的三位数：9*9*8=648个
        // n=3时，有91+648=739个
        for (int i = 2; i <= 10; i++) {
            int count = 9;
            for (int j = 9; j > 9 - i + 1; j--) {
                count *= j;
            }
            dp[i] = dp[i - 1] + count;
        }
        if (n > 10) {
            return dp[10];
        }
        return dp[n];
    }
}