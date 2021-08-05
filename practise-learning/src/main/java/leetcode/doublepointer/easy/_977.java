package leetcode.doublepointer.easy;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 已按 非递减顺序 排序
 *  
 * <p>
 * 进阶：
 * <p>
 * 请你设计时间复杂度为 O(n) 的算法解决本问题
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/5 20:03
 **/
public class _977 {

    public static void main(String[] args) {
        int[] nums = new int[]{-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares(nums)));
    }

    public static int[] sortedSquares(int[] nums) {

        // 双指针遍历nums数组，从头部l和尾部r开始扫描，直到l>r结束扫描
        // 每次扫描后，l或者r的平方都将是更大的数，放入res后，较大值l后移或者较大值r前移，相等则移动l (r也行)
        int n = nums.length;
        int l = 0, r = n - 1;
        int[] res = new int[n];
        // 用于记录res数组已经赋值的索引，从从尾部最大值往前面最小值依次赋值
        int k = nums.length - 1;
        // 双指针从头部和尾部往中间同时扫描数组
        while (l <= r) {
            int ll = nums[l] * nums[l];
            int rr = nums[r] * nums[r];
            if (ll > rr) {
                l++;
                res[k--] = ll;
            } else if (ll < rr) {
                r--;
                res[k--] = rr;
            } else {
                l++;
                res[k--] = ll;
            }
        }
        return res;
    }
}