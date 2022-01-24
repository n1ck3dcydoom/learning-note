package daimasuixianglu._3_hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _9_3Sum {

    public static void main(String[] args) {
        int[] nums = new int[] { -2,0,0,2,2 };
        List<List<Integer>> res = threeSum(nums);
        int a = 1;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        // 排序+双指针
        Arrays.sort(nums);
        for (int i = 0; i <= n - 3; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去重，对l后面和r前边的数，过滤掉相同的
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return res;
    }
}
