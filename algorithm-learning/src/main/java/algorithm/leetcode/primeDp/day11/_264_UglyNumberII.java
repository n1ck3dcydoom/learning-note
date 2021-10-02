package algorithm.leetcode.primeDp.day11;

public class _264_UglyNumberII {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(11));
    }

    public static int nthUglyNumber(int n) {
        // 第一步、定义dp数组
        // 定义dp[n]表示第n个丑数
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // 1通常被视为丑数
        dp[1] = 1;

        // 第三步、状态转移
        // 考虑第i个丑数dp[i]，由前面dp[k]*2或*3或*5得到
        // 即dp[i]=min(dp[k2]*2, min(dp[k3]*3, dp[k5]*5))
        int k2 = 1, k3 = 1, k5 = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[k2] * 2, Math.min(dp[k3] * 3, dp[k5] * 5));
            // 得到2，3，5倍数的丑数后，取最小的，并且移动选择的指针
            //            if (dp[k2] * 2 == dp[i]) {
            //                k2++;
            //            } else if (dp[k3] * 3 == dp[i]) {
            //                k3++;
            //            } else if (dp[k5] * 5 == dp[i]) {
            //                k5++;
            //            }
            // 这里不能用if-else if结构
            // 例如对于丑数6，它在2*3的时候已经计算得到了
            // 对于3*2仍然会把它再次计算一遍
            // 所以在if(dp[k2]*2 == 6)的时候，k2++
            // if(dp[k2]*3 == 6)的时候，也需要k3++
            if (dp[k2] * 2 == dp[i]) {
                k2++;
            }
            if (dp[k3] * 3 == dp[i]) {
                k3++;
            }
            if (dp[k5] * 5 == dp[i]) {
                k5++;
            }
        }
        return dp[n];
    }
}