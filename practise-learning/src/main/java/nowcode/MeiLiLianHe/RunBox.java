package nowcode.MeiLiLianHe;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-13
 * Time: 14:29
 * Description:
 * 有一个X*Y的网格，小团要在此网格上从左上角到右下角，只能走格点且只能向右或向下走。请设计一个算法，计算小团有多少种走法。
 * 给定两个正整数int x,int y，请返回小团的走法数目。
 * <p>
 * 解题思路：
 * 对方向编号，向上是0，向右是1，那么从左下角走到右上角一定要经过M 个1和N个0。这个题目可以转化为从M+N个盒子中挑出M个盒子有多少种方法。
 * 就是C(M+N, M)，或者C(M+N, N)。
 *
 * 直接求m和n的组合数可能会溢出，这道题有待考虑其他不用组合数计算的解法
 */
public class RunBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputString = scanner.nextLine().split(" ");
        int m = Integer.parseInt(inputString[0]);
        int n = Integer.parseInt(inputString[1]);

        if (m > m + n / 2) {
            m = n;
        }

        long denominator = 1;
        for (int i = 1; i <= m; i++) {
            denominator *= i;
        }
        long numerator = 1;
        for (int i = m + n, j = 1; j <= m; i--, j++) {
            numerator *= i;
        }

        System.out.println(numerator / denominator);
    }
}
