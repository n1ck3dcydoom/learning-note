package leetcode.greedy.easy;

public class _747_LargestNumberAtLeastTwiceOthers {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4 };
        System.out.println(dominantIndex(nums));
    }

    public static int dominantIndex(int[] nums) {
        // 找最大数和次大数
        // 判断 次大数*2<=最大数 是否成立
        int fmax = -1, smax = -1;
        int fmaxPos = -1;
        // 遍历一遍,同时维护最大数和次大数
        for (int i = 0; i < nums.length; i++) {
            // 比最大数还要大,则更新最大数和次大数
            // 先把最大数赋值给次大数
            // 在更新最大数
            if (nums[i] > fmax) {
                smax = fmax;
                fmax = nums[i];
                fmaxPos = i;
            }
            // 小于最大数,但是大于次大数
            // 更新次大数
            else if (nums[i] > smax) {
                smax = nums[i];
            }
            // 比次大数还要小,啥也不做
        }
        return smax * 2 <= fmax ? fmaxPos : -1;
    }
}
