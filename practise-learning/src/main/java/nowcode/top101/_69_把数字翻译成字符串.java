package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 16:56
 * Description:
 */

public class _69_把数字翻译成字符串 {

    public static void main(String[] args) {
        System.out.println(solve("12"));
        System.out.println(solve("31717126241541717"));
        System.out.println(solve("10"));
        System.out.println(solve("100"));
        System.out.println(solve("1"));
        System.out.println(solve("0"));
    }

    public static int solve(String nums) {
        int n = nums.length();
        if (n == 1) {
            return 1;
        }

        char[] cs = nums.toCharArray();
        // 第一步定义 dp 数组,定义 dp[i] 表示字符串以 i 结尾时不同的翻译数
        int[] dp = new int[n + 1];

        // 第二步初始值,只有一个数字时,只有一种翻译数
        dp[0] = 1;
        dp[1] = 1;


        // 第三步状态转移方程,考虑字符串以 i 结尾时 dp[i]
        // 如果单独翻译 s[i],则 dp[i] = dp[i-1] + 1
        // 如果 s[i] 和 s[i-1] 一起翻译,则 dp[i] = dp[i-2] + 1
        // 一起翻译的时候要判断 s[i]s[i-1] 是否在 a~z 当中
        // dp[i] = dp[i-1] + dp[i-2] + 1
        for (int i = 2; i <= n; i++) {
            // 如果不是 0 则可以单独翻译
            if (cs[i - 1] != '0') {
                dp[i] = dp[i - 1];
            }
            // s[i] 和 s[i-1] 一起翻译,00 ~ 09 以及 27 ~ 99 无法一起翻译
            int pre = (cs[i - 2] - '0') * 10 + (cs[i - 1] - '0');
            if (10 <= pre && pre <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
