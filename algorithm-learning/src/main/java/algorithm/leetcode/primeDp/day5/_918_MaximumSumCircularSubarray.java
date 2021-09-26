package algorithm.leetcode.primeDp.day5;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/26 22:46
 **/
public class _918_MaximumSumCircularSubarray {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int dpmax = nums[0];
        int max = nums[0];
        int dpmin = nums[0];
        int min = nums[0];
        int sum = nums[0];

        for (int i = 2; i <= n; i++) {
            sum += nums[i - 1];
            dpmax = dpmax < 0 ? nums[i - 1] : dpmax + nums[i - 1];
            max = Math.max(max, dpmax);
            dpmin = dpmin < 0 ? dpmin + nums[i - 1] : nums[i - 1];
            min = Math.min(min, dpmin);
        }

        return Math.max(max, sum - min);
    }
}