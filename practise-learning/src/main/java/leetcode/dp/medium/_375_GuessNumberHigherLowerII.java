package leetcode.dp.medium;

public class _375_GuessNumberHigherLowerII {

    public static void main(String[] args) {

        System.out.println(getMoneyAmount(10));
        //        System.out.println(getMoneyAmount(1));
        //        System.out.println(getMoneyAmount(2));
    }

    public static int getMoneyAmount(int n) {
        // 分析问题
        // f(i,j)表示在数字范围i~j内确保能够获胜的最小金额
        // 如果猜x，且猜错，需要花费x元
        // 如果目标比x大，则需要在x+1~j里面继续猜，需要花费金额总数为x + f(x+1,j)
        // 如果目标比x小，则需要在i~x-1里面继续猜，需要花费金额总数为x + f(i,x-1)
        // 为了确保最终能够获胜，需要取f(x+1,j)和f(i,x-1)的最大值
        // 即f(i,j)=x+max(f(x+1,j), f(i,x-1))
        // 为了求得1~n的能够获胜的最小金额，需要遍历所有x
        // f(1,n)=min(x+max(f(x+1,j), f(1,x-1))) 1 <= x <= n
        // 此时直接递归枚举所有情况
        //        return dfs(1, n);
        return dfs1(1, n, new int[n + 1][n + 1]);
    }

    private static int dfs(int i, int j) {
        // 区间[i,j]决定了i<=j
        // 当i==j时，区间[i,j]只有一个数，不会存在猜错的情况，所以不需要支付任何金额
        if (i >= j) {
            return 0;
        }
        // 枚举区间[i,j]内的每一个x
        int res = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            res = Math.min(res, k + Math.max(dfs(i, k - 1), dfs(k + 1, j)));
        }
        return res;
    }

    private static int dfs1(int i, int j, int[][] memo) {
        if (i >= j) {
            return 0;
        }
        // 朴素递归搜索存在大量重复计算，加上备忘录
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        // 枚举区间[i,j]内的每一个x
        int res = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            res = Math.min(res, k + Math.max(dfs1(i, k - 1, memo), dfs1(k + 1, j, memo)));
        }
        memo[i][j] = res;
        return res;
    }

    public static int getMoneyAmount2(int n) {
        // 使用动态规划改写记忆化递归搜索

        // 第一步、定义dp数组
        // 定义dp[i][j]表示在数字范围i~j内确保能够获胜所需要的最小金额
        // +1预留0，保证金额i能够和dp数组下标i对应
        // +2预留后面的j+1数组越界问题
        int[][] dp = new int[n + 2][n + 2];

        // 第二步、初始值
        // 只有数字1的时候，区间为[1,1]，此时需要的最小金额为0
        // 当左右端点相等的时候，即i=j时，区间内只有一个数，需要的最小金额为0
        //        for (int i = 1; i <= n; i++) {
        //            dp[i][i] = 0;
        //        }

        // 第三步、状态转移
        // 二维dp打表先考虑下打表范围
        // 区间i左端点必须小于等于j右端点，即i<=j，则打表只用填充上半部分
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else {
                    // 遍历区间[i,j]内的每一个x
                    for (int k = i; k <= j; k++) {
                        int temp = Math.max(dp[k + 1][j], dp[i][k - 1]);
                        dp[i][j] = Math.min(dp[i][j], temp);
                    }
                }
            }
        }
        // 正向打表里面发现，计算dp[i][j]的时候，依赖dp[k+1][j]和dp[i][k-1]
        // 此时dp[i][k-1]已经计算完成，但是dp[k+1][j]还没有计算过，因为k+1>i
        // 所以需要逆向打表

        // i==n时，处于对角线上，作为初始值已经计算
        // i从n-1开始遍历
        for (int i = n - 1; i >= 1; i--) {
            // 当i==j时，初始状态已经计算过，j从i+1开始遍历
            for (int j = i + 1; j <= n; j++) {
                // 初始化dp[i][j]为最大值
                dp[i][j] = Integer.MAX_VALUE;
                // 遍历区间[i,j]内的每一个x
                for (int k = i; k <= j; k++) {
                    // 当j==n, k==j时，dp[k+1][j]数组越界
                    // 解决的办法是定义dp数组的时候，在n后面再多开辟一行一列
                    // 即dp[][]=new int[n+2][n+2]

                    // 直接求dp[i][j]和f(i,j)的最小值，结果永远是0，因为dp[i][j]初始化为0
                    // 需要在外面先初始化dp[i][j]为最大值
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[k + 1][j], dp[i][k - 1]));
                }
            }
        }
        return dp[1][n];
    }
}
