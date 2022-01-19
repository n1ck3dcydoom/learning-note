package leetcode.hash.easy;

import java.util.HashMap;
import java.util.Map;

public class _219_ContainsDuplicateII {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 1 };
        System.out.println(containsNearbyDuplicate(nums, 3));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                if (Math.abs(i - hash.get(nums[i])) <= k) {
                    return true;
                }
                hash.put(nums[i], i);
            }
        }
        return false;
    }
}
