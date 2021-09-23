package algorithm.leetcode.primeDp.day4;

public class _45_JumpGameII {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jump1(nums));
    }

    public static int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示到达n所需要的最少步数
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 0;

        // 第三步、递推表达式
        // 考虑第i层，需要检查前面每一个dp[k]，如果能够从k走到i，那么dp[i]可能等于dp[k]+1
        // 求所有能够走到i的k的最小值
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 如果能够从j走到i
                if (j + nums[j - 1] >= i) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[n];
    }

    public static int jump1(int[] nums) {
        // 上述方法仍然是O(n^2)的，考虑优化下

        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 贪心
        // 从当前位置出发，能够走到i+nums[i]的位置
        // 检查i+1,i+2,...,i+nums[i]的每个值，更新能够从k走到的更远位置，然后选择从i走到这个位置k

        // 上一次能够走到的最远位置
        int max = 0;
        int cnt = 0;
        // 从i出发能够走到的最远位置
        int end = 0;
        for (int i = 0; i < n - 1; i++) {
            // 找到从i出发能够走到更远的位置
            max = Math.max(max, i + nums[i]);
            // 走到能够到达的最远位置
            if (i == end) {
                // 更新下一次能够走到的最远位置
                end = max;
                cnt++;
            }
        }
        return cnt;
    }
}