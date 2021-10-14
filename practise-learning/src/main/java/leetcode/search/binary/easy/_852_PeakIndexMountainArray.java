package leetcode.search.binary.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 13:23
 **/
public class _852_PeakIndexMountainArray {

    public static void main(String[] args) {
        int[] nums = new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        System.out.println(peakIndexInMountainArray(nums));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        // O(logn)复杂度，二分查找
        int n = arr.length;

        // 查找区间是[l,r]
        int l = 0, r = n - 1;
        // 结束时，l=r+1，查找区间[r+1,r]为空
        while (l <= r) {
            // 防止溢出
            int mid = l + (r - l) / 2;
            // 如果nums[mid]<nums[mid+1]，则说明mid右侧仍然上升，缩小左侧区间
            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
            }
            // mid左侧仍然上升，缩小右区间
            else if (arr[mid - 1] > arr[mid]) {
                r = mid - 1;
            }
            // 右侧下降，左侧上升，找到谷峰
            else {
                return mid;
            }
        }
        return l;
    }
}