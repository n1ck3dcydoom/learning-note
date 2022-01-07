package daimasuixianglu._1_array;

public class _5_ValidPerfectSquare {

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(5));
        System.out.println(isPerfectSquare(1));
    }

    public static boolean isPerfectSquare(int num) {
        // 二分查找
        int l = 1;
        int r = num;
        while (l <= r) {
            int mid = (r + l) >>> 1;
            if (mid == num / mid) {
                // 完全平方数x的平方根能够被x整除
                if (num % mid == 0) {
                    return true;
                }
                // 如果不能够整除，例如mid=2 num=5
                // 此时mid=num/mid，但是mid还是偏小，需要右移左端点
                l = mid + 1;
            } else if (mid < num / mid) {
                l = mid + 1;
            } else if (mid > num / mid) {
                r = mid - 1;
            }
        }
        return false;
    }
}
