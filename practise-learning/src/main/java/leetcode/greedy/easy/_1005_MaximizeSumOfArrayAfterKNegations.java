package leetcode.greedy.easy;

import java.util.Arrays;

public class _1005_MaximizeSumOfArrayAfterKNegations {

    public static void main(String[] args) {
        int[] n1 = new int[]{4, 2, 3};
        System.out.println(largestSumAfterKNegations(n1, 1));

        int[] n2 = new int[]{3, -1, 0, 2};
        System.out.println(largestSumAfterKNegations(n2, 3));

        int[] n3 = new int[]{2, -3, -1, 5, -4};
        System.out.println(largestSumAfterKNegations(n3, 2));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        int sum = 0;
        // 贪心可以考虑配合排序一起使用
        Arrays.sort(nums);
        // 扫描一遍找到负数的个数
        int nCount = 0;
        for (int i : nums) {
            if (i < 0) {
                nCount++;
            }
        }
        // 分情况讨论：
        // 1、如果k<=nCount，则翻转前k个负数
        if (k <= nCount) {
            int i = 0;
            for (; i < k; i++) {
                sum += Math.abs(nums[i]);
            }
            for (; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum;
        }
        // 2、如果k>nCount，则翻转所有负数，k-=nCount
        // 先翻转所有负数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = Math.abs(nums[i]);
            }
        }
        k -= nCount;
        // 2.1、如果k是偶数，则返回步骤2之后数组和
        if (k % 2 == 0) {
            for (int i : nums) {
                sum += i;
            }
            return sum;
        }
        // 2.2、如果k是奇数，则再步骤2之后挑一个最小的数翻转再求和
        else {
            // 再次排序
            Arrays.sort(nums);
            // 反转第一个数
            nums[0] = -nums[0];
            for (int i : nums) {
                sum += i;
            }
            return sum;
        }
    }
}
