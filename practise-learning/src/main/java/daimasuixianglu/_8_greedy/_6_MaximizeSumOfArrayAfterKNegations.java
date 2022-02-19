package daimasuixianglu._8_greedy;

import java.util.Arrays;

public class _6_MaximizeSumOfArrayAfterKNegations {

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -2, -3};
        System.out.println(largestSumAfterKNegations(nums, 4));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 贪心
        // 首先把尽可能小的负数尽可能的翻转为整数
        // 如果k已经使用完，返回此时的数组和
        // 如果所有负数都已经反转为整数，k仍然有剩余
        // 考虑还有奇数个k，则翻转最小的数
        // 考虑还有偶数个k，啥也不做，直接返回此时的数组和
        int n = nums.length;
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < n && nums[i] < 0 && k > 0; i++, k--) {
            nums[i] = -nums[i];
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (k % 2 == 0) {
            return sum;
        } else {
            Arrays.sort(nums);
            return sum - 2 * nums[0];
        }
    }
}
