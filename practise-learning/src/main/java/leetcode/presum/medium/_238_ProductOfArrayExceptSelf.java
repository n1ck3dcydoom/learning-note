package leetcode.presum.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/21 20:05
 **/
public class _238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // 使用前缀和 后缀和保存中间结果
        int n = nums.length;

        // 因为索引0前面没有元素，所以pre[0]的值等于1
        int[] pre = new int[n];
        pre[0] = 1;
        // 求i前面的所有乘积
        for (int i = 1; i < n; i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }

        // 因为最后一个元素索引n-1的后面没有元素了，所以back[n-1]的值等于1
        int[] back = new int[n];
        back[n - 1] = 1;
        // 求i后面所有的乘积
        for (int i = n - 2; i >= 0; i--) {
            back[i] = nums[i + 1] * back[i + 1];
        }

        // 有了索引i前面和后面的所有元素乘积之后，计算res数组
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = pre[i] * back[i];
        }

        return res;
    }
}