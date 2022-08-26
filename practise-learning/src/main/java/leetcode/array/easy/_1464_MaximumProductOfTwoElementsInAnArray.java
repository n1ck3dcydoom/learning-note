package leetcode.array.easy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/8/26
 * Time: 09:05
 * Description:
 */

public class _1464_MaximumProductOfTwoElementsInAnArray {

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{3, 4, 5, 2}));
        System.out.println(maxProduct(new int[]{1, 5, 4, 5}));
        System.out.println(maxProduct(new int[]{3, 7}));
    }

    public static int maxProduct(int[] nums) {
        // 找到最大和次大的整数,找到最小和次小的负数
        Arrays.sort(nums);

        int n = nums.length;
        int max1 = nums[n - 1];
        int max2 = nums[n - 2];
        int min1 = nums[0];
        int min2 = nums[1];
        return Math.max((max1 - 1) * (max2 - 1), (min1 - 1) * (min2 - 1));
    }
}
