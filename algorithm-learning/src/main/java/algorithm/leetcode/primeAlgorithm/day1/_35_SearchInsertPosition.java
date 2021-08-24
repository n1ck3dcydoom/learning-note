package algorithm.leetcode.primeAlgorithm.day1;

public class _35_SearchInsertPosition {
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, 5, 6 };
        int res = searchInsert(nums, 7);
        System.out.println(res);
    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        // 搜索区间[l, r];
        int l = 0, r = n - 1;
        // 结束时l=r+1，搜索区间为[r+1, r]，搜索区间为空
        while (l <= r) {
            // 防止溢出
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
