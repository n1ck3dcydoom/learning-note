package leetcode.dp.hard;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/27 23:30
 **/
public class _639_DecodeWaysII {

    public static void main(String[] args) {
        String s1 = "*";
        String s2 = "1*";
        String s3 = "2*";
        String s4 = "7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*";
        //        System.out.println(numDecodings(s1));
        //        System.out.println(numDecodings(s2));
        //        System.out.println(numDecodings(s3));
        System.out.println(numDecodings(s4));
    }

    private static int MOD = 1000000007;

    public static int numDecodings(String s) {
        char tmp = s.charAt(0);
        if (tmp == '0') {
            return 0;
        }
        int len = s.length();
        long[] dp = new long[len + 1];
        dp[0] = 1;
        dp[1] = tmp == '*' ? 9 : 1;
        for (int i = 2; i <= len; i++) {
            char ch = s.charAt(i - 1);
            if (ch == '*') {
                dp[i] = 9 * dp[i - 1];
                if (tmp == '*') {
                    dp[i] += 15 * dp[i - 2];
                } else if (tmp == '1') {
                    dp[i] += 9 * dp[i - 2];
                } else if (tmp == '2') {
                    dp[i] += 6 * dp[i - 2];
                }
            } else if (ch == '0') {
                dp[i] = dp[i - 2];
                if (tmp == '*') {
                    dp[i] += dp[i - 2];
                } else if (tmp > '2' || tmp == '0') {
                    return 0;
                }
            } else if (ch > '6') {
                dp[i] = dp[i - 1];
                if (tmp == '*' || tmp == '1') {
                    dp[i] += dp[i - 2];
                }
            } else {
                dp[i] = dp[i - 1];
                if (tmp == '*') {
                    dp[i] += 2 * dp[i - 2];
                } else if (tmp == '1' || tmp == '2') {
                    dp[i] += dp[i - 2];
                }
            }
            dp[i] %= 1000000007;
            tmp = ch;
        }
        return (int) dp[len];
    }
}