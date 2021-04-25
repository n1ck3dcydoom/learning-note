package leetcode.search.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/4/25 23:28
 **/
public class _33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int n = nums.length;

        // 旋转后分为左右两个部分
        // 4 5 6 7 x x x x
        // x x x x 0 1 2 3

        int left = 0;
        int right = n - 1;
        // 二分查找
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 无法直接比较nums[mid]和targe的大小

            // 先比较nums[mid]和nums[left]的大小
            // 确定mid所处的位置是在左半段还是右半段

            // mid所处位置在左半段
            if (nums[mid] >= nums[left]) {
                // 再寻找target在mid的左还是右

                // 如果target大于等于左端点，小于mid端点，一定在左端
                // 例如 3 4 5 6 7 0 1 2 ，查找4
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }
                // 如果target小于左端点，或者大于mid节点(这种情况表示处于旋转后仍处于某一半数组，还没到k分割后的另一个数组那边去)
                // 而且mid节点不可能等于target，因为上面已经判断过了
                else {
                    left = mid + 1;
                }
            }
            // mid所处位置在右半段
            else {
                // 如果target大于mid节点，但是小于等于右端点，一定在右端
                // 例如 6 7 0 1 2 3 4 5 ，查找4
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                }
                // 如果target大于右端点，或者小于mid节点(这种情况表示处于旋转后仍处于某一半数组，还没到k分割后的另一个数组那边去)
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}