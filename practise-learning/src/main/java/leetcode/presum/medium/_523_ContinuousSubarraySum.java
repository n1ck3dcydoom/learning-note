package leetcode.presum.medium;

import java.util.HashMap;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * <p>
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 * <p>
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/2 18:53
 **/
public class _523_ContinuousSubarraySum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(checkSubarraySum1(nums, 5));
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // 定义一个前缀和数组，preSum[i]表示前i-1个元素之和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 遍历行
        for (int i = 0; i < n; i++) {
            // 遍历列
            for (int j = i + 1; j < n; j++) {
                // 如果当前n(i,j)的和是k的倍数则返回true
                int sum = preSum[j] - preSum[i];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
        // 方法时间复杂度为O(n^2)超时了
    }

    public static boolean checkSubarraySum1(int[] nums, int k) {
        int n = nums.length;

        // 定义一个前缀和数组，表示前i个元素之和
        // 优化：后面不会再用到nums数组，可以原地置换节省空间复杂度
        for (int i = 1; i < n; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        // 已知 a+b+c      对k取余等于n    a+b+c = xk+n ①
        // 已知 a+b+c+d+e  对k取余也等于n  a+b+c+d+e = yk+n ②
        // ②-①可得 d+e = (y-x)k
        // 即d+e也是k的倍数

        // 定义一个map表示，key = 余数 ，value = 出现此余数的前缀和下标
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历前缀和数组
        for (int i = 0; i < n; i++) {
            // 当前位置的前缀和的余数
            int remainder = nums[i] % k;

            // 余数等于0的情况特殊判断
            // i>=1表示至少有两个元素
            if (remainder == 0 && i >= 1) {
                return true;
            }

            // 判断当前余数在之前的遍历是否出现过
            // 重复出现时，不能更新下标
            if (map.containsKey(remainder)) {
                // 如果之前出现过，去除之前的下标，判断当前下标和之前的下标长度是否满足2
                // 前缀和数组数组下标是i-1
                // 做差后应该至少要有两个元素
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            }
            // 没出现过则加入map，前缀和数组下标是i
            else {
                map.put(remainder, i);
            }
        }
        return false;
    }

}