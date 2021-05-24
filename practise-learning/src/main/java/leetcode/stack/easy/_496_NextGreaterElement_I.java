package leetcode.stack.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * <p>
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * <p>
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/24 21:03
 **/
public class _496_NextGreaterElement_I {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        nextGreaterElement(nums1, nums2);
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 定义一个单调栈，保存nums2数组的下标

        // 求解的是下一个更大的数，定义一个单调递减栈
        // 因为只有在遇到一个更大的数时，才会弹出栈顶元素

        // 使用map保存nums2数组中每个元素的下一个更大的元素的下标位置
        HashMap<Integer, Integer> numsMap = new HashMap<>();

        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            // 栈内保存nums2的下标
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                // 把比栈顶元素更大的当前元素的下标写入map中
                numsMap.put(nums2[stack.peek()], i);
                // 更新下标
                stack.pop();
            }
            // 弹出比当前元素小的栈顶元素后，把当前元素的索引下标压入栈内
            stack.push(i);
        }

        // 遍历nums1，从numsMap中根据nums1作为key找到对应的下一个更大的元素
        int[] stackNums1 = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            stackNums1[i] = numsMap.getOrDefault(nums1[i], -1) == -1 ? -1 : nums2[numsMap.get(nums1[i])];
        }
        return stackNums1;
    }
}