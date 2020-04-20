package nowcode.PinDuoDuo;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-10
 * Time: 16:38
 * Description:
 * <p>
 * 小镇沿街分布（可理解为在数轴上），有n家银行（位置以数轴的坐标表示，金额表示可以被抢走的钱）
 * 两个强盗试图分别抢劫两个银行，但是两个银行之间的距离必须大于等于d
 * 请问他们能够抢劫的最大金额之和为多少
 * <p>
 * 输入n+1行：
 * 第一行包括两个数字 n 和 d  ( 1 <= n <= 20 0000 , 1 <= d <= 1 0000 0000 )
 * n表示银行数量，d表示两个银行之间的距离
 * <p>
 * 接下来n行每一行两个数字 a 和 b  ( 1 <= a,b <= 1 0000 0000 )
 * 分别表示坐标和金额，空格分割
 * <p>
 * 输出一个数字：
 * 两个绑匪抢劫的最大金额之和
 * <p>
 * 例如：
 * 输入：
 * 6 3
 * 1 1
 * 3 5
 * 4 8
 * 6 4
 * 10 3
 * 11 2
 * <p>
 * 输出：
 * 11
 */
public class _3 {
    public static void main(String[] args) {

        String[] first;
        int n;
        int d;
        String[] temp;
        try (Scanner scanner = new Scanner(System.in)) {
            first = scanner.nextLine().split(" ");
            n = Integer.parseInt(first[0]);
            d = Integer.parseInt(first[1]);
            temp = scanner.nextLine().split(" ");
        }

        int[] bankIndex = new int[n];
        int[] bankMoney = new int[n];
        for (int i = 0; i < n; i++) {
            bankIndex[i] = Integer.parseInt(temp[0]);
            bankMoney[i] = Integer.parseInt(temp[1]);
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            if (bankIndex[0] + d > bankIndex[i]) {
                int j = i;
                while (bankIndex[j] - bankIndex[0] < bankIndex[i] + d) {
                    j++;
                }
                for (int k = j; k < n; k++) {
                    int sum = bankMoney[i] + bankMoney[k];
                    if (sum > max) {
                        max = sum;
                    }
                }
            } else if (bankIndex[0] + d <= bankIndex[i] && bankIndex[i] <= bankIndex[n - 1] - d) {
                int j = i;
                while (bankIndex[i] - bankIndex[j] < d) {
                    j--;
                }
                for (int k = 0; k <= j; k++) {
                    int sum = bankMoney[i] + bankMoney[k];
                    if (sum > max) {
                        max = sum;
                    }
                }
                j = i;
                while (bankIndex[j] - bankIndex[i] < d) {
                    j++;
                }
                for (int k = j; k < n; k++) {
                    int sum = bankMoney[i] + bankMoney[k];
                    if (sum > max) {
                        max = sum;
                    }
                }
            } else {
                int j = i;
                while (bankIndex[i] - bankIndex[j] < d) {
                    j--;
                }
                for (int k = 0; k <= j; k++) {
                    int sum = bankMoney[i] + bankMoney[k];
                    if (sum > max) {
                        max = sum;
                    }
                }
            }
        }
        System.out.println(max);
    }
}
