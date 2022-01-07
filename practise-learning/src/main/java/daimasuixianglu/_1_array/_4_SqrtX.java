package daimasuixianglu._1_array;

public class _4_SqrtX {

    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(3));
    }

    public static int mySqrt(int x) {
        // 二分查找
        if (x == 0) {
            return 0;
        }
        // 查找区间[l,r]
        int l = 1;
        int r = x;
        // 结束时，l=r+1，查找区间[r+1,r]为空
        while (l <= r) {
            // jdk二分查找实现 int mid = (l+r) >>> 1
            int mid = l + (r - l) / 2;
            // 防止mid*mid溢出
            if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                return mid;
            } else if (mid < x / mid) {
                l = mid + 1;
            } else if (mid > x / mid) {
                r = mid - 1;
            }
        }
        return -1;
    }
}
