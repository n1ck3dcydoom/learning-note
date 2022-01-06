package daimasuixianglu._1_array;

/**
 * Created by n!Ck
 * Date: 2022/1/6
 * Time: 23:31
 * Description:
 */

public class _2_SearchInsertPosition {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        System.out.println(searchInsert(nums, 7));
    }

    public static int searchInsert(int[] nums, int target) {
        // 二分查找
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return l;
    }
}
