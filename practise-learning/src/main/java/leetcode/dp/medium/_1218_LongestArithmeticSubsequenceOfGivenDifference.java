package leetcode.dp.medium;

import java.util.HashMap;

public class _1218_LongestArithmeticSubsequenceOfGivenDifference {

    public static void main(String[] args) {
                int[] nums1 = new int[]{1, 2, 3, 4};
                System.out.println(longestSubsequence(nums1, 1));
                int[] nums2 = new int[]{1, 3, 5, 7};
                System.out.println(longestSubsequence(nums2, 1));
                int[] nums3 = new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1};
                System.out.println(longestSubsequence(nums3, -2));
                int[] nums4 = new int[]{7, 7, 7, 7, 7, 7, 7};
                System.out.println(longestSubsequence(nums4, 0));
        int[] nums5 = new int[]{1};
        System.out.println(longestSubsequence(nums5, 1));
    }

    public static int longestSubsequence(int[] arr, int difference) {
        // 最长xx子序列，基本都是dp思路
        int n = arr.length;
        // 第一步、定义dp数组
        // 定义dp[i]表示以arr[i]结尾，且公差为diff的最长等差子序列长度
        int[] dp = new int[n];

        // 第二步、初始值
        // 只有一个元素时长度为1
        dp[0] = 1;
        // 第三步、状态转移
        // 考虑以arr[i]结尾的时候，两种情况
        // 1、arr[i]单独构成子序列，长度为1，dp[i]=1
        // 2、arr[i]和前面某个子序列dp[k]构成新的子序列，长度为dp[i]=dp[k]+1
        int res = dp[0];
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.put(arr[0], 0);
        for (int i = 1; i < n; i++) {
            // 最坏的情况arr[i]自己单独构成子序列
            dp[i] = 1;
            // 往前面寻找能够构成新子序列的情况
            // 剪枝，使用hash保存之前出现过的数和索引
            // 往i前面寻找的时候，从hash里面get(arr[i]-diff)
            if (hash.containsKey(arr[i] - difference)) {
                int index = hash.get(arr[i] - difference);
                dp[i] = Math.max(dp[i], dp[index] + 1);
            }
            // 更新最大值
            res = Math.max(dp[i], res);
            // hash保存索引
            hash.put(arr[i], i);
        }
        return res;
    }
}
