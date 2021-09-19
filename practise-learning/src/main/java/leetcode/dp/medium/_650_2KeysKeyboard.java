package leetcode.dp.medium;

public class _650_2KeysKeyboard {

    public static void main(String[] args) {
        System.out.println(minSteps(18));
    }

    public static int minSteps(int n) {

        // 动态规划，有点点贪心的意思
        // 求n时，可以通过前面n的因数来粘贴若干次得到n
        // 例如，求8个A时，可以通过1个A复制1次，粘贴7次得到
        // 可以通过2个A复制1次，粘贴3次得到
        // 可以通过4个A复制1次，粘贴1次得到

        // 第一步、定义dp[n]表示得到n个A最少需要的操作次数
        // n+1额外保存0的情况
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 0个A时，需要的操作次数为0
        // 1个A时，需要的操作次数为0（初始状态就有1个A）
        dp[0] = 0;
        dp[1] = 0;

        // 第三步、递推表达式
        // 考虑dp[i]，求前面所有因数dp[k]+1+(i-k)/k
        // 求因数时，只需要计算到i的算术平方根即可
        for (int i = 2; i <= n; i++) {
            // dp[i]的最大值即为每次粘贴1个A，需要的操作次数为复制1次+(i-1)次粘贴，共i次
            dp[i] = i;
            // 只要能够被i整除的，都是i的因数
            // for (int j = 1; j <= i / 2; j++) {
            // if (i % j == 0) {
            // dp[i] = Math.min(dp[i], dp[j] + 1 + (i - j) / j);
            // }
            // }
            // 从最大的因数开始找，找到第一个就退出
            for (int j = i / 2; j >= 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + 1 + (i - j) / j;
                    break;
                }
            }
        }

        return dp[n];
    }
}
