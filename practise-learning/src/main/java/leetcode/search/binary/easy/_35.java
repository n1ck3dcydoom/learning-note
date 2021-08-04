package leetcode.search.binary.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 * <p>
 * 输入: nums = [1], target = 0
 * 输出: 0
 *  
 * <p> c
 * 提示:
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 为无重复元素的升序排列数组
 * -10^4 <= target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/4 16:09
 **/
public class _35 {

    public int searchInsert(int[] nums, int target) {
        // 二分查找
        int n = nums.length;
        // 搜索区间是[l,r]
        int l = 0, r = n - 1;
        // 结束时l=r+1，搜索区间为[r+1,r]，搜索区间为空
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 已经搜索过mid，需要去掉
                l = mid + 1;
            } else if (nums[mid] > target) {
                // 已经搜索过mid，需要去掉
                r = mid - 1;
            }
        }
        return l;
    }
}