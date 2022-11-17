package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 16:08
 * Description:
 */

public class _18_二维数组当中的查找 {

    public boolean Find(int target, int[][] array) {
        // 每行二分
        for (int[] arr : array) {
            if (binarySearch(arr, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] nums, int t) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == t) {
                return true;
            } else if (nums[mid] < t) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
