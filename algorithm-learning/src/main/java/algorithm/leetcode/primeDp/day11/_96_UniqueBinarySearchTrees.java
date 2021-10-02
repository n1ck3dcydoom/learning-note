package algorithm.leetcode.primeDp.day11;

public class _96_UniqueBinarySearchTrees {

    public int numTrees(int n) {
        // 这题不看题解能做？
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        // 初始值
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        // 状态转移
        // 考虑以i结尾的所有bst树
        // 分别以1~i为头节点构建不同的bst树
        // 如果root=1，则左子树节点个数为0，右子树节点个数为i-1，dp[i]=dp[0]*dp[i-1]
        // 如果root=2，则左子树节点个数为1，右子树节点个数为i-2，dp[i]=dp[1]*dp[i-2]
        // 如果root=3，则左子树节点个数为2，右子树节点个数为i-3，dp[i]=dp[2]*dp[i-3]
        // ......
        // 如果root=j (1<=j<=i)，则左子树节点个数为j-1，右子树节点个数为i-j，dp[i]=dp[3]*dp[i-j]
        // ......
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}