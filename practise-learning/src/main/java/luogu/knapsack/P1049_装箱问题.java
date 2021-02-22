package luogu.knapsack;

import java.util.Scanner;

/**
 * @author zhanglei
 * @version 1.0
 * @description luogu
 * @date 2021/2/22 21:59
 **/
public class P1049_装箱问题 {
    public static void main(String[] args) {
        // 装入箱子内使得箱子的剩余空间最小，可以转化为装入最多的物品到箱子里
        // 01背包问题
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        //        int[] w = new int[]{8, 3, 12, 7, 9, 7};
        //        int V = 24;
        //        int n = 6;
        System.out.println(solution(n, w, V));
    }

    /**
     * @param n 物品个数
     * @param w 每个物品c的体积
     * @param V 给定大小为V的背包
     */
    private static int solution(int n, int[] w, int V) {
        // 朴素01背包，不优化

        // 定义dp[i][j]表示把前i个物品“恰好”装入容量大小为j的背包，可以取得的最大价值
        int[][] dp = new int[n + 1][V + 1];

        // 初始值，背包容量为0的背包，什么物品都装不下，价值是0，遍历物品
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包，背包容量从1到给定的大小V，空背包0已经成为初始值了
            for (int j = 1; j <= V; j++) {
                // 当前背包容量能否装下当前物品
                if (j >= w[i - 1]) {
                    // 这里没有物品的价值，很容易发现物品的价值等于物品的体积
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + w[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // 这里求到了每种容量的背包，能够装下体积和最大的物品
        // 题目求的是剩余体积，所以用V减去最大体积和
        return V - dp[n][V];
    }

    private static int solution1(int n, int[] w, int V) {
        // 使用滚动数组优化
        int[][] dp = new int[2][V + 1];

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包，背包容量从1到给定的大小V，
            for (int j = 1; j <= V; j++) {
                if (j >= w[i - 1]) {
                    dp[i % 2][j] = Math.max(dp[(i + 1) % 2][j], dp[(i + 1) % 2][j - w[i - 1]] + w[i - 1]);
                } else {
                    dp[i % 2][j] = dp[(i + 1) % 2][j];
                }
            }
        }
        // 从第一个物品开始，奇数放到数组1，偶数放到数组0
        return V - dp[n % 2][V];
    }

    private static int solution2(int n, int[] w, int V) {
        // 优化到一维数组
        int[] dp = new int[V + 1];

        // 遍历物品
        for (int i = 1; i <= n; i++) {
            // 遍历背包，倒叙遍历保证每次遍历到i的时候，i-1仍然是上一次计算的值，而没有被重新修改
            // j只遍历到w[i-1]，因为背包容量小于物品体积时，肯定装不进去
            //            for (int j = V; j >= 1; j--) {
            for (int j = V; j >= w[i - 1]; j--) {

                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + w[i - 1]);
                }
            }
        }
        return V - dp[V];
    }
}