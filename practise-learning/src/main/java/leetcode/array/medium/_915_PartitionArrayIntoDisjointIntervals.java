package leetcode.array.medium;

/**
 * Created by n!Ck
 * Date: 2022/10/24
 * Time: 21:44
 * Description:
 */

public class _915_PartitionArrayIntoDisjointIntervals {

    public static void main(String[] args) {
        System.out.println(partitionDisjoint(new int[]{5, 0, 3, 8, 6}));
        // System.out.println(partitionDisjoint(new int[]{1, 1, 1, 0, 6, 12}));
    }

    public static int partitionDisjoint(int[] nums) {
        // 一定存在一个点使得左边最大的数小于等于右边最小的数
        // 从后往前遍历,记录每个位置往后出现的最小的数
        int n = nums.length;
        int[] mins = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            mins[i] = min;
        }
        // 从前往后遍历,记录每个位置出现过的最大的数 max
        // 找到第一个 max<= mins[k] 的位置 k
        // 即为第一次出现左边的所有数都小于等于右边的所有数
        // 且 k 的长度尽可能小
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (max <= mins[i]) {
                return i;
            }
            max = Math.max(max, nums[i]);
        }
        return -1;
    }
}
