package leetcode.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/12/17 19:49
 **/
public class _152_MaximumProductSubarray {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 定义dm[i]为以i结尾的最长乘积最大的连续子数组
        int[] dm = new int[n];
        dm[0] = nums[0];
        // 定义dn[i]为以i结尾的最长乘积最小的连续子数组
        int[] dn = new int[n];
        dn[0] = nums[0];
        // 遍历构造dm和dn数组
        for (int i = 1; i < n; i++) {
            dm[i] = Math.max(Math.max(dm[i - 1] * nums[i], dn[i - 1] * nums[i]), nums[i]);
            dn[i] = Math.min(Math.min(dm[i - 1] * nums[i], dn[i - 1] * nums[i]), nums[i]);
        }
        int max = dm[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, dm[i]);
        }
        return max;
    }
}
