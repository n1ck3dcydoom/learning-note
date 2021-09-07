package algorithm.leetcode.primeDataStructure.day2;

import java.util.HashMap;

public class _1_TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 7, 11, 15 };

        int[] res = twoSum(nums, 9);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res + " ");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        // 使用map保存nums的每个元素
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { i, map.get(target - nums[i]) };
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
