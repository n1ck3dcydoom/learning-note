package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/9/7 22:51
 **/
public class _300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3, 4, 6, 9, 7, 8};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * 假设第i个数的最长上升子序列(LIS)长度为f(i)
     * 如果存在 n (i < n),且 a[i] < a[n]
     * 那么把 a[n] 放到 a[i] 的后面，一定能够构造出一个LIS，其长度等于 f(i) + 1
     * <p>
     * 状态转移方程：f(n) = max{f(i)} + 1 (0 < i < n 且 a[i] < a[n])
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        // 考虑极端情况，如果给定数组nums是个递减数组
        // 那么以每个元素结尾的LIS就是其本身，长度为1
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = 1;
        }
        // 从第二个元素开始遍历
        for (int i = 1; i < n; i++) {
            int temp = 0;
            // 从第一个元素开始遍历到元素i，找里面Max{f(j)}
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(res[j], temp);
                }
            }
            // 更新i的f(n)值
            res[i] = temp + 1;
        }
        int length = 0;
        for (int i = 0; i < n; i++) {
            length = Math.max(length, res[i]);
        }
        return length;
    }
}