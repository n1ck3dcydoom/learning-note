package leetcode.presum.medium;

/**
 * @author zhanglei
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
}