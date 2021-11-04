package leetcode.search.binary.easy;

public class _367_ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(5));
    }

    public static boolean isPerfectSquare(int num) {
        // 预处理所有完全平方数不通过，会超时

        // 二分法找有没有两个数相乘等于num的
        // 查找区间是[l,r]
        int l = 1, r = num;
        // 结束时，l=r+1，查找区间[r+1,r]为空
        while (l <= r) {
            // mid*mid 会超过int范围
            //            if (mid * mid == num) {
            //                return true;
            //            } else if (mid * mid < num) {
            //                l = mid + 1;
            //            } else if (mid * mid > num) {
            //                r = mid - 1;
            //            }
            int mid = l + (r - l) / 2;
            int tmp = num / mid;
            if (tmp == mid) {
                if (num % mid == 0) {
                    return true;
                }
                l = mid + 1;
            }
            // mid偏小
            else if (mid < tmp) {
                l = mid + 1;
            }
            // mid偏大
            else if (mid > tmp) {
                r = mid - 1;
            }
        }
        return false;
    }
}
