package daimasuixianglu._3_hash;

import java.util.HashMap;
import java.util.Map;

public class _8_TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[] { 3,2,4 };
        int[] res = twoSum(nums, 6);
        int a = 1;
    }

    public static int[] twoSum(int[] nums, int target) {
        // hash map保存所有数字出现的位置
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hash.put(nums[i], i);
        }
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i]) && i != hash.get(target - nums[i])) {
                res[0] = i;
                res[1] = hash.get(target - nums[i]);
                return res;
            }
        }
        return res;
    }
}
