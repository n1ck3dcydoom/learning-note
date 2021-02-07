package luogu.knapsack;

/**
 * @author zhanglei
 * @version 1.0
 * @description luogu
 * https://www.luogu.com.cn/problem/P1048
 * @date 2021/2/7 16:47
 **/
public class P1048_采药 {

    public static void main(String[] args) {

        int[] c = new int[]{71, 69, 1};
        int[] w = new int[]{100, 1, 2};
        int v = 70;
        System.out.println(test(c, w, v));

    }


    private static int test(int[] c, int[] w, int v) {
        if (c == null || c.length == 0) {
            return 0;
        }
        int n = c.length;
        // 第一步，定义dp数组
        // 设F[i][v]表示，前i个物体 “恰好” 放入容量为v的背包，其最大价值
        int[][] F = new int[n + 1][v + 1];

        // 第二步，初始值
        // 所有物品放入背包容量为0的，价值肯定是0，因为没有物品能够放进去
        for (int i = 0; i <= n; i++) {
            F[i][0] = 0;
        }

        // 第三步，选择，即递推表达式
        // F[i][v] = Max(F[i-1][v], F[i-1][v-c[i]]+w[i])
        // 枚举物品 0 <= i < n
        for (int i = 1; i <= n; i++) {
            // 枚举前i个物品 “恰好” 能够放入1、2、3、... 、v的背包
            // 枚举容量 1 <= j <= v
            for (int j = 1; j <= v; j++) {
                // 对第i件物品做出选择
                // 当前背包容量放不下第i件物品，自然不会放入，则与前i-1件物品相关
                if (j < c[i - 1]) {
                    F[i][j] = F[i - 1][j];
                }
                // 如果当前背包能够放入第i件物品，则考虑要不要放入
                else {
                    F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - c[i - 1]] + w[i - 1]);
                }
            }
        }
        // 返回容量为v时的最大价值
        return F[n][v];
    }
}
