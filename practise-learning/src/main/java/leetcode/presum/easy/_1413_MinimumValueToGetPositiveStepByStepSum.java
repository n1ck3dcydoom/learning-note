package leetcode.presum.easy;

/**
 * Created by n!Ck
 * Date: 2022/8/9
 * Time: 08:56
 * Description:
 */

public class _1413_MinimumValueToGetPositiveStepByStepSum {
    public int minStartValue(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 找到最小前缀和,返回其绝对值+1
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];

        int min = pre[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            min = Math.min(min, pre[i]);
        }

        // 若最小前缀和小于 0,返回其绝对值+1
        // 若大于 0,返回 1
        return min < 0 ? Math.abs(min) + 1 : 1;
    }
}
