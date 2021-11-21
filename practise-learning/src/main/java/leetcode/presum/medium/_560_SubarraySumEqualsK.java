package leetcode.presum.medium;

import java.util.HashMap;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/26 22:00
 **/
public class _560_SubarraySumEqualsK {
    /**
     * index    0 1 2 3  4  5  6  7
     * nums     1 3 4 2  6  2  8  0
     * preSum 0 1 4 8 10 16 18 26 26
     * pIndex 0 1 2 3 4  5  6  7  8
     * 当i=3，j=6时，求nums[3]+...+nums[6]的和
     * num[3]+...+nums[6] = preSum[6+1] - preSum[3] = preSum[7] - preSum[3]
     * = 26 - 8 = 18 = 2 + 6 + 2 + 8 = 18
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 求nums的前缀和数组
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // 遍历nums和nums的前缀和数组
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (preSum[j + 1] - preSum[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 求nums的前缀和数组
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // 使用字典优化前缀和出现的次数存储
        HashMap<Integer, Integer> map = new HashMap<>();
        // K = 3
        // index 0 1 2  3  4  5
        // nums  1 2 6  4 -1  5
        // pre 0 1 3 9 13 12 17
        // 当遍历到i=1的时候，pre=3，此时需要找map中有没有前缀和=0的
        // 即pre = 3 - 3 = 0 的key，次数为1
        // 所以需要开始初始化map放入(0,1)
        // 表示 当前前缀和=K的时候，需要找map中有没有pre-K的key
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(preSum[i + 1] - k)) {
                count += map.get(preSum[i + 1] - k);
            }
            // 如果map里面含有pre-K的前缀和，则让他次数+1
            // 如果没有，则放入次数1
            map.put(preSum[i + 1], map.getOrDefault(preSum[i + 1], 0) + 1);
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 使用字典优化前缀和出现的次数存储
        HashMap<Integer, Integer> map = new HashMap<>();
        // K = 3
        // index 0 1 2  3  4  5
        // nums  1 2 6  4 -1  5
        // pre 0 1 3 9 13 12 17
        // 当遍历到i=1的时候，pre=3，此时需要找map中有没有前缀和=0的
        // 即pre = 3 - 3 = 0 的key，次数为1
        // 所以需要开始初始化map放入(0,1)
        // 表示 当前前缀和=K的时候，需要找map中有没有pre-K的key

        // 连前缀和数组都不用保存了，反正每次只关心map里面有没有前缀和-K的key存在
        map.put(0, 1);
        int count = 0;
        int n = nums.length;
        // 保存当前的前缀和
        int preSum = 0;
        for (int i = 0; i < n; i++) {
            // 求当前的前缀和
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            // 如果map里面含有pre-K的前缀和，则让他次数+1
            // 如果没有，则放入次数1
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}