package daimasuixianglu._3_hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _11_4SumII {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, -2, -5, -4, -3, 3, 3, 5 };
        List<List<Integer>> res = fourSum(nums, -11);
        int a = 0;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 双指针降维

        List<List<Integer>> res = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        // 剪枝，如果排序后最小的数都比target大，则不存在满足题意的四元组
        // if (nums[0] > target) {
        // return res;
        // }
        // 遍历
        for (int i = 0; i < nums.length; i++) {
            // i去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int k = i + 1; k < nums.length; k++) {
                // k去重
                if (k > i + 1 && nums[k - 1] == nums[k]) {
                    continue;
                }
                // 双指针遍历最后两个数
                int l = k + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[k] + nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[k], nums[l], nums[r]));
                        // 收缩左右指针，同时去重
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r - 1] == nums[r]) {
                            r--;
                        }
                        // 同时收缩
                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
