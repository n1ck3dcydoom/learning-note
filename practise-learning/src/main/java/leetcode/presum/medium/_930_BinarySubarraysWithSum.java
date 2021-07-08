package leetcode.presum.medium;

import java.util.HashMap;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * <p>
 * 子数组 是数组的一段连续部分。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/8 17:12
 **/
public class _930_BinarySubarraysWithSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 0, 1};
        int[] nums1 = new int[]{0, 0, 0, 0, 0};
        System.out.println(numSubarraysWithSum1(nums1, 0));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {

        int n = nums.length;
        // 前缀和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        // 两层循环找到所有前缀和差等于goal的结果
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (preSum[j] - preSum[i - 1] == goal) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int numSubarraysWithSum1(int[] nums, int goal) {

        int n = nums.length;
        // 前缀和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        // 两层循环找到所有前缀和差等于goal的结果
        // 两层for循环超时了，寻求时间复杂度更低的方法，前缀和思路没有变

        // 使用hash表保存以前出现过的前缀和次数，每次遍历i时，寻找goal-nums[i]在hash表里出现的次数
        HashMap<Integer, Integer> hash = new HashMap<>();
        hash.put(0, 1);
        for (int j = 1; j <= n; j++) {
            // 右端点是j，左端点是i，寻找pre[j]-pre[i]=goal的i
            // pre[i]=pre[j]-goal
            // 即left=pre[j]-goal
            int left = preSum[j] - goal;

            // 从hash表里寻找left出现的次数
            res += hash.getOrDefault(left, 0);
            // 更新当前前缀和到hash中
            hash.put(preSum[j], hash.getOrDefault(preSum[j], 0) + 1);
        }

        return res;
    }
}