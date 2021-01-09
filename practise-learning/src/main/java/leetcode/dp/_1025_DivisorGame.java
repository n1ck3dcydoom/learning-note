package leetcode.dp;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2021/1/9 23:45
 **/
public class _1025_DivisorGame {
    public boolean divisorGame(int N) {
        // 如果N小于等于0，先手没得选，必输
        if (N <= 0) {
            return false;
        }
        if (N == 1) {
            return false;
        }
        if (N == 2) {
            return true;
        }
        // 第一步、定义dp数组
        // dp[i] 表示当N=i时，先手的人是否会取得胜利
        boolean[] dp = new boolean[N + 1];

        // 第二步、初始值
        dp[0] = false; // 数字N=0，先手没得选，先手必输
        dp[1] = false; // 数字N=1，先手没得选，先手必输
        dp[2] = true;  // 数字N=2，先手选1，后手没得选，先手必胜

        // 第三步、选择，递推表达式
        // 若N = 2 = 2 * 1
        // 先手选择1，后手必输，dp[2] = true
        // 若N = 3 = 3 * 1
        // 先手选择1，后手得到2，即dp[3-1]，后手必胜，即dp[3] = false
        // 若N = 4 = 2 * 2 * 1
        // 若先手选择2，后手得到2，即dp[2]，后手必胜
        // 若先手选择1，后手得到3，即dp[3]，后手必输
        // 即dp[4] = !dp[4-2] || !dp[4-1] = true
        // 若N = 5 = 5 * 1
        // 先手选择1，后手得到4，即dp[4]，后手必胜，即dp[5] = !dp[5-1] = false
        // 即dp[5] = !dp[5-1] = false
        // 若N = 6 = 3 * 2 * 1
        // 若先手选择1，则后手得到5，即dp[5]，后手必输，即dp[6] = !dp[6-1] = true
        // 若先手选择2，则后手得到4，即dp[4]，后手必胜，即dp[6] = !dp[6-2] = false
        // 若先手选择3，则后手得到3，即dp[3]，后手必输，即dp[6] = !dp[6-3] = true

        // 综上所述，只要先手从N中的取出的因数存在一个让自己获胜的，则自己一定能够获胜
        // 即dp[i] = !dp[i-x1] || !dp[i-x2] || ... || !dp[i-xn]
        // x1, x2, ..., xn 为i的因数(0<x<i)
        for (int i = 3; i <= N; i++) {
            // 每次x只能是i的因数
            for (int x = 1; x <= i / 2; x++) {
                if (i % x == 0) {
                    if (!dp[i - x]) {
                        // 只要找到一个因数能够让自己获胜，则退出循环
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[N];
    }
}