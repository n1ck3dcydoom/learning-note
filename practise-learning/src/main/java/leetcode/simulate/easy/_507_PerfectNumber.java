package leetcode.simulate.easy;

/**
 * Created by n!Ck
 * Date: 2022/1/5
 * Time: 0:36
 * Description:
 */

public class _507_PerfectNumber {

    public static void main(String[] args) {
        System.out.println(checkPerfectNumber(28));
    }

    public static boolean checkPerfectNumber(int num) {
        // 直接暴力枚举给定n的所有因数
        // int sum = 0;
        // for (int i = 1; i <= num / 2; i++) {
        //     if (num % i == 0) {
        //         sum += i;
        //     }
        // }
        // return sum == num;

        // 计算因数只用遍历到平方根
        int sum = 1;
        // 不能从1开始，因为后面1*num==num成立，会把num加入因数列表求和
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                // 加上当前因数
                sum += i;
                // 加上当前因数的另一个乘积因数
                // 避免5*5=25，导致5计算了两次，完全平方数的平方根只记录一次，就是上面的sum+i
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        // 1不是完美数，特殊处理
        return sum == 1 ? false : sum == num;
    }
}
