package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 16:04
 * Description:
 */

public class _17_二分查找 {

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        // 查找区间是 [l,r],结束时 l=r+1,此时查找区间为 [r+1,r],查找区间为空
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
