package daimasuixianglu._9_dp;

public class _28_MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 2, 8};
        int[] nums2 = new int[]{5, 6, 1, 4, 7};
        System.out.println(findLength(nums1, nums2));
    }

    public static int findLength(int[] nums1, int[] nums2) {
        // 动态规划
        // 第一步、定义dp数组
        // 定义dp[i][j]表示数组1以i结尾，数组2以j结尾能构成的最长重复子数组的长度
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        // 第二步、状态转移方程
        // 考虑数组1以i结尾，数组2以j结尾的情况
        // 如果nums1[i]==nums2[j]，则dp[i][j]=dp[i-1][j-1]+1
        // 若nums1[i]!=nums2[j]，则dp[i][j]=0


        // 第三步、初始状态
        // 一般二维dp相当于打表的过程，第0行和第0列通常都是初始状态
        // 第0行，nums1不选数字，nums2遍历完
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        // 第0列，nums1遍历完，nums2不选数字
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }

        int res = dp[1][1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
