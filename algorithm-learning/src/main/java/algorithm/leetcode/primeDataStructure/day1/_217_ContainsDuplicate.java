package algorithm.leetcode.primeDataStructure.day1;

import java.util.HashSet;

public class _217_ContainsDuplicate {

    public static void main(String[] args) {
        int[] n1 = new int[] { 1, 2, 3, 1 };
        int[] n2 = new int[] { 1, 2, 3, 4 };
        int[] n3 = new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
        System.out.println(containsDuplicate(n1));
        System.out.println(containsDuplicate(n2));
        System.out.println(containsDuplicate(n3));
    }

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;
        // 使用set去重
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                return true;
            }
        }
        return false;
    }
}
