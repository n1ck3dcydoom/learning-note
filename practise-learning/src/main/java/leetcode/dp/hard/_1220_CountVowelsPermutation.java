package leetcode.dp.hard;

import java.util.Arrays;

public class _1220_CountVowelsPermutation {

    public static void main(String[] args) {
        System.out.println(countVowelPermutation2(1));
        System.out.println(countVowelPermutation2(2));
        System.out.println(countVowelPermutation2(5));
        System.out.println(countVowelPermutation2(144));
        System.out.println(countVowelPermutation2(158));
    }

    public static int countVowelPermutation(int n) {
        // 动态规划

        // 第一步,定义dp数组
        // 定义dp[i][5]表示以aeiou结尾的字符串个数
        long[][] dp = new long[n + 1][5];

        // 第二步,初始值
        // 长度为1的时候,以aeiou结尾的字符串只有1个
        for (int i = 0; i < 5; i++) {
            dp[1][i] = 1;
        }

        // 第三步,状态转移
        // 考虑长度为i的字符串,有五种选择aeiou
        // dp{a:0,e:1,i:2,o:3,u:4}
        // 若i以a结尾,则i-1只能是e/i/u,则dp[i][0]=dp[i-1][1]+dp[i-1][2]+dp[i-1][4] // a = e+i+u
        // 若i以e结尾,则i-1只能是a/i,则dp[i][1]=dp[i-1][0]+dp[i-1][2]; // e = a+i
        // 若i以i结尾,则i-1只能是e/o,则dp[i][2]=dp[i-1][1]+dp[i-1][3] // i = e+o
        // 若i以o结尾,则i-1只能是i,则dp[i][3]=dp[i-1][2] // o = i
        // 若i以u结尾,则i-1只能是i/o,则dp[i][4]=dp[i-1][2]+dp[i-1][3] // u = i+o
        final int MOD = 1000000007;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % MOD;
            dp[i][3] = (dp[i - 1][2]) % MOD;
            dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % MOD;
        }
        long res = 0;
        for (int i = 0; i < 5; i++) {
            res += dp[n][i] % MOD;
        }
        return (int) (res % MOD);
    }

    public static int countVowelPermutation2(int n) {
        // 发现dp[i][k]的状态之和dp[i-1][k']相关,可以使用滚动数组压缩空间
        long[] dp = new long[5];
        long[] ndp = new long[5];
        // 初始状态长度为1
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }
        final int MOD = 1000000007;
        for (int i = 2; i <= n; i++) {
            ndp[0] = (dp[1] + dp[2] + dp[4]) % MOD;
            ndp[1] = (dp[0] + dp[2]) % MOD;
            ndp[2] = (dp[1] + dp[3]) % MOD;
            ndp[3] = (dp[2]) % MOD;
            ndp[4] = (dp[2] + dp[3]) % MOD;
            // 更新滚动数组
            dp = Arrays.copyOf(ndp, 5);
        }
        long res = 0;
        for (int i = 0; i < 5; i++) {
            res += (dp[i]) % MOD;
        }
        return (int) (res % MOD);
    }
}
