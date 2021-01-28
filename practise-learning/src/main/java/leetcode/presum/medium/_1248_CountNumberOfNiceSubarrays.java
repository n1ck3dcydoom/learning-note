package leetcode.presum.medium;

import java.util.HashMap;

/**
 * @author zhanglei
 * @version 1.0
 * @description leetcode
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/28 19:40
 **/
public class _1248_CountNumberOfNiceSubarrays {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 6};
        System.out.println(numberOfSubarrays(nums, 1));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 求nums的前缀和
        // index      0 1 2 3 4 5 6 7 8 9
        // nums       2 2 2 1 2 2 1 2 2 2
        // preIndex 0 1 2 3 4 5 6 7 8 9 10
        // preSum   0 0 0 0 1 1 1 2 2 2 2

        // 找前缀和差等于k的所有组合
        // 求i=1，j=6之间的前缀和数组
        // preSum[6+1] - preSum[1] = preSum[7] - preSum[1] = 2 - 0 = 2
        // 求i=0，j=6之间的前缀和数组
        // preSum[6+1] - preSum[0] = preSum[7] - preSum[0] = 2 - 0 = 2

        // 使用hash表存储pre[j+1] - k = pre[i]的次数
        // 需要注意，若k=5，当前前缀和pre[i] = 5，那么需要查找k-pre[i] = 5-5 = 0 的次数
        // 所以需要初始化map(0,1)
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        // 当前下标i的前缀和(奇数个数)
        int preSum = 0;
        // 遍历原数组
        for (int num : nums) {
            // 计算当前下标的前缀和
            preSum += num % 2 == 0 ? 0 : 1;
            // 如果hash表里已经有preSum-k的值，则count增加
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }
            // 保存当前前缀和出现的次数
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);

        }
        return count;
    }
}