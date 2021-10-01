package algorithm.leetcode.primeDp.day10;

public class _91_DecodeWays {

    public static void main(String[] args) {
        System.out.println(numDecodings(""));
    }

    public static int numDecodings(String s) {
        int res = 0;
        int n = s.length();
        // 第一步、定义dp数组
        // 定义dp[n]表示s串以n结尾的合法编码种数
        int[] dp = new int[n + 1];

        // 0开头属于非法情况
        if (s.charAt(0) == '0') {
            return 0;
        }
        // 第二步、初始值
        dp[0] = 1;
        // 只有一个数字就只有一种编码
        dp[1] = 1;

        // 第三步、状态转移
        // 考虑以s[i]结尾的时候
        // 1、s[i]==0，则s[i]不能单独解码，只能和s[i-1]一起解码
        // 1.1 s[i-1]=0，非法
        // 1.2 s[i-1]=1~2，只有10和20，共两种，dp[i]+=dp[i-2]
        // 1.3 s[i-1]=3~9，非法

        // 2、s[i]=1~9
        // 若s[i]单独解码，则有1种，dp[i]+=dp[i-1]
        // 若s[i]和s[i-1]一起解码
        // 2.1 s[i-1]=0，非法
        // 2.2 s[i-1]=1，s[i-1]=0~9，共10种，dp[i]+=dp[i-2]
        // 2.3 s[i-1]=2，s[i-1]=0~6，共7种，dp[i]+=dp[i-2]
        // 2.4 s[i-1]=3~9，非法

        char[] chars = s.toCharArray();
        for (int i = 2; i <= n; i++) {
            char cur = chars[i - 1];
            char pre = chars[i - 2];

            // s[i]等于0时必须和s[i-1]一起解码
            if (cur == '0') {
                if (pre == '0' || ('3' <= pre && pre <= '9')) {
                    dp[i] += 0;
                } else {
                    dp[i] += dp[i - 2];
                }
            } else {
                // 若s[i]单独解码
                dp[i] += dp[i - 1];
                // 若s[i]和s[i-1]一起解码
                if (pre == '0') {
                    dp[i] += 0;
                } else if (pre == '1') {
                    dp[i] += dp[i - 2];
                } else if (pre == '2') {
                    // s[i]只能取1~6
                    if ('1' <= cur && cur <= '6') {
                        dp[i] += dp[i - 2];
                    }
                } else {
                    dp[i] += 0;
                }
            }
        }
        return dp[n];
    }
}