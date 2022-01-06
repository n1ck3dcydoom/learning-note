package daimasuixianglu._1_array;

/**
 * Created by n!Ck
 * Date: 2022/1/6
 * Time: 23:36
 * Description:
 */

public class _3_FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] res = searchRange(nums, 8);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        // 查找第一个出现target的位置，和最后一个出现target的位置
        // 二分查找
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int left = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 如果相等，为了找到第一个，仍然需要缩小右端点的范围
            if (nums[mid] == target) {
                r = mid - 1;
                // 找到了target才更新端点值
                left = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }

        l = 0;
        r = n - 1;
        int right = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 如果相等，为了寻找最后一个出现target的位置，仍然需要扩大左端点的范围
            if (nums[mid] == target) {
                l = mid + 1;
                // 找到了target才更新端点值
                right = mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return new int[]{left, right};
    }
}
