package algorithm.leetcode.primeAlgorithm.day1;

/*
 * @lc app=leetcode.cn id=704 lang=java
 *
 * [704] 二分查找
 */

public class _704_BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[] { -1,0,3,5,9,12 };
        int res = search(nums, 2);
        System.out.println(res);
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        // 搜索区间是[l, r]
        int l = 0, r = n - 1;
        // 结束时l=r+1，搜索区间是[r+1, r]，搜索区间为空
        while (l <= r) {
            // 避免直接 (l+r)/2 溢出
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 中间值偏小，收缩左区间，mid已经搜索过，l需要去掉mid
                l = mid + 1;
            } else if (nums[mid] > target) {
                // 中间值偏大，收缩右区间，mid已经搜索过，r需要去掉mid
                r = mid - 1;
            }
        }
        return -1;
    }
}
