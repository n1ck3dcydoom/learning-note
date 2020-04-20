package nowcode.ByteDance;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-16
 * Time: 10:02
 * Description:
 * <p>
 * 1，4，16，64一共4中面值的硬币，以及1024元的纸币
 * <p>
 * 现用1024的纸币购买一件价值N的商品  ( 0 < N <= 1024 )
 * <p>
 * 问最少能收到多少枚硬币
 * <p>
 * 输入描述：
 * 一行，包含商品价值N
 * <p>
 * 输出描述：
 * 一行，包含最少的硬币数
 * <p>
 * 例如：
 * 200
 * <p>
 * 17
 * <p>
 * 因为1024-200=864  而864可以分为12个64的，3个16的，2个4的
 */
public class _1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = 1024 - scanner.nextInt();

            int count = 0;
            if (n / 64 > 0) {
                count += n / 64;
                n = n - (n / 64) * 64;
            }
            if (n / 16 > 0) {
                count += n / 16;
                n = n - (n / 16) * 16;
            }
            if (n / 4 > 0) {
                count += n / 4;
                n = n - (n / 4) * 4;
            }
            if (n > 0) {
                count += n;
            }
            System.out.println(count);
        }
    }
}
