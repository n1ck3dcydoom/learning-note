package leetcode.sort.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/26 19:42
 **/
public class _34_FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }

        int n = nums.length;
        // 二分查找
        // 找到目标值后，从目标值的左右两侧开始递减和递增寻找第一个不等于target的索引位置
        int left = 0;
        int right = n - 1;
        // 记录二分查找找到的第一个target的索引位置
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 二分查找，找到第一个target的索引值后直接break
            if (nums[mid] == target) {
                index = mid;
                break;
            }
            // 没找到继续二分
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 如果二分查找都没有找到第一个target的索引值，说明数组中不存在target值，直接返回-1, -1
        if (index == -1) {
            return res;
        }
        // 找到第一个target的索引值后，分别往左右两边搜索第一个不等于target的索引值
        left = index;
        right = index;
        // 判断left的左边的数是否等于target，或者左边的是否到达边界
        while (left - 1 >= 0 && nums[left - 1] == target) {
            left--;
        }
        // 判断right的右边的数是否等于target，或者右边是否到达边界
        while (right + 1 < n && nums[right + 1] == target) {
            right++;
        }
        res[0] = left;
        res[1] = right;
        return res;
    }
}