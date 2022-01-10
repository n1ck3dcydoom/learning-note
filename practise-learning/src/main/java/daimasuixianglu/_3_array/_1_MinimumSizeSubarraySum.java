package daimasuixianglu._3_array;

public class _1_MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        System.out.println(minSubArrayLen(11, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        // 滑动窗口寻找区间
        int l = 0, r = 0;
        // 计算当前窗口内元素的和
        int sum = 0;
        while (r < n) {
            sum += nums[r];
            // 调整窗口左端点
            while (sum >= target) {
                // 更新子数组长度
                res = Math.min(res, r - l + 1);
                // 收缩左端点
                l++;
                // 判断窗口是否合法
                if (l > r) {
                    break;
                }
                // 更新当前窗口的元素和
                sum -= nums[l - 1];
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
