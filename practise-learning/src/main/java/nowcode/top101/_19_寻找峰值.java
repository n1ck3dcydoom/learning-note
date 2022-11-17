package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 16:20
 * Description:
 */

public class _19_寻找峰值 {

    public int findPeakElement(int[] nums) {
        // 对于峰值 k,其左侧一定是递增,右侧一定是递减
        // 二分查找判断 mid 和 mid+1 的关系,题目保证一定不存在 mid = mid+1 的情况
        // 如果 mid > mid+1,说明 mid 右侧处于递减状态,右端点收缩,由于 mid 可能是峰值所以 r=mid
        // 如果 mid < mid+1,则说明 mid 由此处于递增状态,左端点收缩,而且 mid 一定不是峰值所以 l=mid+1

        int l = 0;
        int r = nums.length;
        // 搜索区间为 [l,r) ,结束时 r=l,即 [r,r) ,此时搜索区间为空
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            }
        }
        return r;
    }
}
