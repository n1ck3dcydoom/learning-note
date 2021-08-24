package algorithm.leetcode.primeAlgorithm.day1;

/*
 * @lc app=leetcode.cn id=278 lang=java
 *
 * [278] 第一个错误的版本
 */

/* The isBadVersion API is defined in the parent class VersionControl.
     boolean isBadVersion(int version); */
public class _278_FirstBadVersion {

    public static void main(String[] args) {
        int res = firstBadVersion(5);
        System.out.println(res);
    }

    public static int firstBadVersion(int n) {
        // 搜索区间是[l, r]
        int l = 1, r = n;
        // 结束时l=r+1，搜索区间为[r+1, r]，搜索区间为空
        while (l <= r) {
            // 避免(r+l)/2溢出
            int mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                // 寻找左边界，当mid满足查询条件时，不能立即返回mid，需要继续收缩右边界，找到第一个满足查询条件的左边界才返回
                // mid已经搜索过，收缩r时需要去掉mid
                r = mid - 1;
            } else {
                // 如果mid不是一个错误版本，继续收缩左边界
                // mid已经搜索过，收缩l时需要去掉mid
                l = mid + 1;
            }
        }
        // 如果不存在错误版本，最后l=r+1，导致l比n大1，需要特殊处理下
        return l > n ? -1 : l;
    }

    public static boolean isBadVersion(int version) {
        return false;
    }
}
