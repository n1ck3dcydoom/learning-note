package leetcode.dp.medium;

public class _673_NumberOfLongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums1));

        int[] nums2 = new int[]{2, 2, 2, 2, 2};
        System.out.println(findNumberOfLIS(nums2));
    }

    public static int findNumberOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        // 第一步、定义dp数组
        // 定义dp[n]表示以nums[n]结尾的最长递增子序列 "长度"
        // n+1额外保存0
        int[] dp = new int[n + 1];

        // 第二步，初始值
        // 数组长度为0时，递增子序列最长为0
        dp[0] = 0;
        // 数组长度为1时，递增子序列最长为1
        dp[1] = 1;

        // 第三步、递推表达式
        // 考虑以nums[n]结尾的dp[n]，如果nums[n] > nums[k]，其中0 <= k < n
        // 则nums[n]能够接在nums[k]的末尾构成新的递增子序列
        // 其长度为dp[n] = dp[k] + 1
        // 所以看起来二次遍历需要求得其前面最长的递增子序列
        // 如果前面所有的数都不满足nums[n] > nums[k] 那么dp[n] = 1，即它本身

        // 定义count[i]表示以nums[i]结尾的最长递增子序列的个数
        int[] count = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            count[i] = 1;
        }
        // count[n]如何转移，如果nums[n] > nums[k] ，且dp[k] + 1 > dp[n]
        // 则说明能够加上nums[n]构成更长的递增子序列
        // 那么以nums[n]结尾的最长递增子序列个数count[n]也就等于以nums[k]结尾的子序列个数count[k]
        // 即 count[n] = count[k]
        // 如果nums[n] > nums[k] ，但是dp[k] + 1 == dp[n]
        // 则说明n前面出现了若干个同样长度等于dp[n]的，但是子序列组合情况不同的结果
        // 那么count[n]需要把前面所有长度等于dp[n]的出现的情况都加上
        // 即count[n] += count[k]

        int max = 0;
        for (int i = 2; i <= n; i++) {
            // 最坏的情况就是只有nums[i]本身构成递增子序列
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] < nums[i - 1]) {
                    // dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                    // 前面出现了多个长度同样为dp[j]的，但是子序列不同的情况，需要累加
                    else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
            // 更新最大的子序列长度
            max = Math.max(max, dp[i]);
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] == max) {
                res += count[i];
            }
        }
        return res;
    }
}