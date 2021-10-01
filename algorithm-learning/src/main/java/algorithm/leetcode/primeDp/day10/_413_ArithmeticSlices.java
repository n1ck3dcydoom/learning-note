package algorithm.leetcode.primeDp.day10;

public class _413_ArithmeticSlices {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(numberOfArithmeticSlices(nums));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        // 第一步、定义dp数组
        // 定义dp[i]表示以i结尾的，有效的等差数列子数组个数
        // 子数组长度必须大于等于3
        int[] dp = new int[n + 1];

        // 第二步，初始值
        dp[0] = 0;
        // 前两个数字肯定不能构成长度大于等于3的等差数列
        dp[1] = 0;
        dp[2] = 0;

        // 第三步、状态转移
        // 考虑以n[i]结尾的dp[i]
        // 如果加上n[i]也能够构成新的等差数列
        // dp[i]=dp[i-1]+1
        int res = 0;
        for (int i = 3; i <= n; i++) {
            // 判断前三个数是否能够构成等差数列
            if (nums[i - 1] - nums[i - 2] == nums[i - 2] - nums[i - 3]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }
}