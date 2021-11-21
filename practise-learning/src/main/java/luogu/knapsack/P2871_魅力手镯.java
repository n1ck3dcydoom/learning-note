package luogu.knapsack;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description luogu
 * https://www.luogu.com.cn/problem/P2871
 * @date 2021/2/10 21:52
 **/
public class P2871_魅力手镯 {
    public static void main(String[] args) {
        //        int n;
        //        int v;
        //
        //        Scanner scanner = new Scanner(System.in);
        //        // 第一行共两个数，第一个是物品个数，第二个是背包容量
        //        n = scanner.nextInt();
        //        v = scanner.nextInt();
        //
        //        // 后面n行共两个数，第一个是物品的体积，第二个数是物品的价值
        //        int[] c = new int[n];
        //        int[] w = new int[n];
        //        for (int i = 0; i < n; i++) {
        //            c[i] = scanner.nextInt();
        //            w[i] = scanner.nextInt();
        //        }
        //        System.out.println(test(c, w, v));
        int[] c = new int[]{1, 2, 3, 2};
        int[] w = new int[]{4, 6, 12, 7};
        int v = 6;
        System.out.println(test(c, w, v));

    }

    private static int test(int[] c, int[] w, int v) {
        // 简单01背包问题，直接使用一维的dp数组
        int n = c.length;
        int[] F = new int[v + 1];

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // “倒叙”遍历背包容量
            for (int j = v; j >= 1; j--) {
                if (j >= c[i - 1]) {
                    F[j] = Math.max(F[j], F[j - c[i - 1]] + w[i - 1]);
                }
            }
        }
        return F[v];
    }
}