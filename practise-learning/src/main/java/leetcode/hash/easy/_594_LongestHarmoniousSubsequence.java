package leetcode.hash.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _594_LongestHarmoniousSubsequence {

    public static void main(String[] args) {
        int[] ns1 = new int[]{1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(findLHS1(ns1));
        //        int[] ns2 = new int[]{1, 2, 3, 4};
        //        System.out.println(findLHS1(ns2));
        //        int[] ns3 = new int[]{1, 1, 1, 1};
        //        System.out.println(findLHS1(ns3));
    }

    public static int findLHS(int[] nums) {
        // 子序列问题，可以看作是对于输入的离散数据nums
        // 挑选出其中的若干个数，构成一个子序列

        // 扫描输入数组，进行hash计数
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int n : nums) {
            hash.put(n, hash.getOrDefault(n, 0) + 1);
        }
        // 遍历hash中的每个key，检查key+1
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (hash.containsKey(entry.getKey() + 1)) {
                res = Math.max(res, entry.getValue() + hash.get(entry.getKey() + 1));
            }
        }
        return res;
    }

    public static int findLHS1(int[] nums) {
        // 遍历nums中的每一个数x
        // 对于x，它可以和x+1构成和谐子序列
        // 此时只需要统计x和x+1的个数即可
        // 排序
        Arrays.sort(nums);
        // 滑动窗口检查每个元素
        int res = 0;
        // start指向所有x子序列的头部
        int start = 0;
        // end指向所有x+1子序列的尾部
        int end = 0;
        // 和谐子序列的长度等于end和start之间的元素个数end-start+1
        while (end < nums.length) {
            // 找到所有x，移动end右端点
            if (nums[end] == nums[start]) {
                end++;
            } else {
                // 如果nums[s]和nums[e]无法构成和谐子序列，移动窗口左端点
                while (end >= start && nums[end] - nums[start] > 1) {
                    start++;
                }
                // 直到nums[s]和nums[e]构成和谐子序列
                if (nums[end] - nums[start] == 1) {
                    res = Math.max(res, end - start + 1);
                }
                // 找到x+1后，移动右端点，找到所有x+1
                end++;
            }
        }
        return res;
    }
}
