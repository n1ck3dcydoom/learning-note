package algorithm.leetcode.primeDp.day5;

public class _53_MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        // 考虑到dp[i]的状态只会由dp[i-1]转移过来
        // 所以不需要定义dp数组，只需要一个变量pre即可

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int pre = nums[0];
        int max = nums[0];

        for (int i = 2; i <= n; i++) {
            pre = pre < 0 ? nums[i - 1] : pre + nums[i - 1];
            max = Math.max(max, pre);
        }
        return max;
    }
}