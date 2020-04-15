package leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by n!Ck
 * Date: 2019-02-26
 * Time: 15:17
 * Description:
 * <p>
 * 1. 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 实现方法原型public int[] twoSum(int[] nums, int target)
 */
public class _1_TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }

    // 方法一：暴力遍历
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    private static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // 方法二：哈希表存储
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int anotherNum = target - nums[i];
            if (numsMap.containsKey(anotherNum)) {
                return new int[]{numsMap.get(anotherNum), i};
            }
            numsMap.put(nums[i], i);
        }
        return new int[]{};
    }
}
