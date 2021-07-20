package leetcode.search.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/20 11:28
 **/
public class _704_BinarySearch {
    public int search(int[] nums, int target) {
        // 二分查找

        int n = nums.length;
        int l = 0, r = n - 1;
        // 查找区间是 [l, r] 左闭右闭，结束时l = r + 1  搜索区间是 [l+1, l] 搜索区间为空
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 搜索区间是[l, r]，已经搜索过mid了，移动两端端点时需要去掉mid
                // 即[mid+1, r]
                l = mid + 1;
            } else if (nums[mid] > target) {
                // 同上，过滤掉已经搜索过的mid
                r = mid - 1;
            }
        }
        return -1;
    }
}