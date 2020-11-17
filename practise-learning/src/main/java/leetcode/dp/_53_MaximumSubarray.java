package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/11/17 14:55
 **/
public class _53_MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    /**
     * 第一步：定义状态
     * 定义dp[i]为以i结尾的连续子数组的最大和
     * 第二步：转移方程
     *         { dp[i-1]+num[i]   dp[i-1]>=0  如果前面i-1个子数组的和大于0的话，加上第i个数，是一定大于以i结尾的子序列和的
     * dp[i] = {
     *         { num[i]           dp[i-1]<0   如果前面i-1个子数组的和小于0的话，加上第i个数，是一定小于以i结尾的子序列和的，这个时候最大值就是num[i]自己
     * 第三步：初始状态
     * dp[0]=num[0]
     */
    public static int maxSubArray(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        // 初始状态
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}