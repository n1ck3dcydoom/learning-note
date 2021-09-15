package leetcode.search.binary.medium;

public class _162_FindPeakElement {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 1 };
        System.out.println(findPeakElement(nums));
    }

    public static int findPeakElement(int[] nums) {
        // O(logn)时间复杂度，二分查找
        int l = 0, r = nums.length - 1;
        // nums[-1] = nums[n] = -oo
        // 如果中间某个值nums[i]，满足nums[i]>nums[i-1]或者nums[i]>nums[i+1]
        // 那么一定存在以i为波峰的峰值

        // 查找区间是[l,r]，结束时查找区间为[r+1,r]，查找区间为空

        while (l < r) {
            int mid = l + (r - l) / 2;
            // 一直没想明白如何放弃在另一边里面寻找峰值
            // 由于题目给出条件有nums[i] != nums[i+1]
            // 通过比较mid和mid+1的大小
            // 如果mid>mid+1，那么在[0,mid]范围内是一定存在峰值的，从而放弃[mid+1,r]
            // 如果mid<mid+1，那么在[mid+1,r]范围内是一定存在峰值的，从而放弃[0,mid]
            if (nums[mid] > nums[mid + 1]) {
                // 若mid大于mid+1，则mid仍然有可能成为峰值
                // 即mid-1<mid>mid+1的情况
                // 所以移动右端点时，要保留mid的结果
                r = mid;
            } else if (nums[mid] < nums[mid + 1]) {
                // 若mid小于mid+1，那么mid一定不可能成为峰值
                // 移动左端点时就可以去掉mid的结果
                l = mid + 1;
            }
        }
        return l;
    }
}
