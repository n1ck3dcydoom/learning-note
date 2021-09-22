package algorithm.leetcode.primeDp.day3;

public class _740_DeleteAndEarn {

    public static void main(String[] args) {
        //        int[] nums1 = new int[]{3, 4, 2};
        //        System.out.println(deleteAndEarn(nums1));
        int[] nums2 = new int[]{2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarn(nums2));
    }

    public static int deleteAndEarn(int[] nums) {
        int res = 0;
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示删除前n个数能够获得的最大点数
        //        int[] dp = new int[n + 1];
        //        int[] numsBack = Arrays.copyOf(nums, n);

        // 第二步、初始值
        //        dp[0] = 0;

        // 第三步、递推表达式
        // 考虑第i个数dp[i]，有两个选择，删掉或者不删
        // 1、如果i能够删掉，则说明前面没有删掉nums[i]-1和nums[i]+1
        // dp[i] = dp[i-1] + nums[i]
        // 2、如果i不能够删掉，dp[i] = dp[i-1]


        // 我是傻逼

        // 统计每个数字出现的次数
        // 找到最大值
        int max = 0;
        for (int i : nums) {
            max = Math.max(max, i);
        }
        int[] cnt = new int[max + 1];
        for (int i : nums) {
            cnt[i]++;
        }

        // 定义dp[i][0/1]，dp[i][0]删掉i，dp[i][1]选择i
        int[][] dp = new int[max + 1][2];

        for (int i = 1; i <= max; i++) {
            // 如果删掉i，那么状态由i-1转移过来，而i-1有删或者不删，取最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // 如果选择i，那么i-1一定不能删，而且可以同时选择所有i
            dp[i][1] = dp[i - 1][0] + i * cnt[i];
        }

        return Math.max(dp[max][0], dp[max][1]);
    }
}