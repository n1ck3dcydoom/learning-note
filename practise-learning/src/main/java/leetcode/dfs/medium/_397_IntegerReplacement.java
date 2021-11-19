package leetcode.dfs.medium;

import java.util.HashMap;

public class _397_IntegerReplacement {

    public static void main(String[] args) {
        System.out.println(integerReplacement2(8));
        System.out.println(integerReplacement2(Integer.MAX_VALUE));
        //        System.out.println(integerReplacement(7));
        //        System.out.println(integerReplacement(4));
    }

    public static int integerReplacement(int n) {
        // 第一步、定义dp数组
        // 定义dp[0][i]、dp[1][i]分别表示
        // 定义dp[i]表示将i通过整数替换到1所需要的最少操作步数
        //        int[] dp = new int[n + 1];

        // 第二步、初始值
        //        dp[1] = 0;

        // 第三步、状态转移
        // 考虑第i个数
        // 若i是偶数，最少的操作步骤就是一直i = i / 2
        // 即dp[i]=dp[i/2]+1
        // 若i是奇数，则需要多花费1步把它变成偶数
        // 由两端的偶数的较小值决定
        // 即dp[i]=min(dp[i+1]+1, dp[i-1]+1)
        // 这里需要扩展dp数组的长度为dp[n+1]
        // +1 预留0的位置，保证下面的i和数组下标i对应

        if (n == 1 || n == 2) {
            return n - 1;
        }

        int[] dp = new int[(n % 2 == 0 ? n : n + 1) + 1];
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) {
                // 先把后一个偶数dp[i+1]求得
                dp[i + 1] = dp[(i + 1) / 2] + 1;
                // 再求dp[i]
                dp[i] = Math.min(dp[i + 1], dp[i - 1]) + 1;
            }
        }
        return dp[n];
    }

    public static int integerReplacement1(int n) {
        return dfs(n);
    }

    private static int dfs(long n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + dfs(n / 2);
        } else {
            // 对于输入n=Integer.MAX_VALUE
            // n+1会溢出，把n强制转换为long类型处理
            return 1 + Math.min(dfs(n + 1), dfs(n - 1));
        }
    }

    public static int integerReplacement2(int n) {
        // 朴素dfs递归存在大量重复计算
        // 加入备忘录缓存中间结果
        return dfs1(n, new HashMap<Long, Integer>());
    }

    private static int dfs1(long n, HashMap<Long, Integer> memo) {
        if (n == 1) {
            return 0;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n % 2 == 0) {
            int t = 1 + dfs1(n / 2, memo);
            memo.put(n, t);
            return t;
        } else {
            // 对于输入n=Integer.MAX_VALUE
            // n+1会溢出，把n强制转换为long类型处理
            int t1 = dfs1(n + 1, memo);
            int t2 = dfs1(n - 1, memo);
            int t = 1 + Math.min(t1, t2);
            memo.put(n, t);
            return t;
        }
    }
}
