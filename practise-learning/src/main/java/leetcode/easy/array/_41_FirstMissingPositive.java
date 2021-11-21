package leetcode.easy.array;

import java.util.HashSet;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * <p>
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/7/9 19:55
 **/
public class _41_FirstMissingPositive {

    /**
     * 把数组放到set里面，从1开始遍历
     * 需要O(n)的额外空间，不满足题意
     *
     * @param nums nums
     * @return 第一个没有出现的最小的整数
     */
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 1;
        while (true) {
            if (!set.contains(i)) {
                break;
            } else {
                i++;
            }
        }
        return i;
    }

    /**
     * 原地hash
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 当前数组的值必须要是正整数
            // 当前数组的值必须要小于等于数组长度

            // 当前数组的值必须要不等于数组下标hash后的值
            // 我们的hash函数：n值 应该放到数组 [n-1] 的位置
            // 例如  nums[i] = 6  那么6 应该放到数组nums[6-1=5] 的位置
            // 所以需要判断nums[nums[i]-1]的值是否等于nums[i]的值，避免重复交换造成的死循环
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int n, int m) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
}