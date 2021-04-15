package leetcode.medium.array;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/15 23:04
 **/
public class _31_NextPermutation {
    public void nextPermutation(int[] nums) {
        // 1、从尾部往前找到第一个上升序列，即k满足  nums[k] < nums[k+1]
        // 如果找到头都找不到，则翻转整个数组

        // 2、从尾部往前找到第一个大于k的值m，即m满足  nums[m] > nums[k]

        // 3、交换nums[k] 和 nums[m]

        // 这样能够找到右边尽可能大，左边尽可能小的两个数，交换后满足新的排列一定大于交换前的排列
        // 但是这个不能保证交换后的排列一定是交换前的 “下一个” 排列
        // 所以还需要找到交换后的最小排列

        // 4、翻转交换后的，以前k后面的所有数

        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;

        // k默认-1，因为0也有可能成为第一个上升序列，nums[0] > nums[1]
        // int k = 0;
        int k = -1;
        // 从后往前找第一个上升序列k的位置
        // i从n-2开始，因为是和后面一项做比较，构成上升或者下降序列，所以从倒数第二项开始遍历
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;
            }
        }

        // 如果找到头都没找到上升序列，说明当前排列是最大排列，按照题意直接翻转整个数组输出zuixiaopailie
        if (k == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // 寻找m
        int m = -1;
        // 从尾部开始往前面寻找比k大的数
        for (int i = n - 1; i >= 0; i--) {
            if (nums[k] < nums[i]) {
                m = i;
                break;
            }
        }
        // 交换找到的k和m
        int temp = nums[k];
        nums[k] = nums[m];
        nums[m] = temp;
        // 翻转交换后k后面的所有数，保证新的排列是交换前的“下一个”排列
        reverse(nums, k + 1, n - 1);
    }

    /**
     * 翻转数组nums从i到j的所有项，包括i和j
     */
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            // 交换i和j
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;

            // i后移，j前移
            i++;
            j--;
            // 偶数项直接交换到最后两个，然后ij相遇，完成翻转
            // 奇数项交换到中间的左右两个，然后ij相遇，中间项不用交换完成翻转
        }
    }
}