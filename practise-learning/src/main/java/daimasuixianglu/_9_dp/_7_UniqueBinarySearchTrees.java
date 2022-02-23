package daimasuixianglu._9_dp;

public class _7_UniqueBinarySearchTrees {

    public int numTrees(int n) {
        // https://programmercarl.com/0096.%E4%B8%8D%E5%90%8C%E7%9A%84%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html#%E6%80%9D%E8%B7%AF

        // 第一步、定义dp数组
        // 定义dp[i]表示节点个数为i的不同BST种数
        int[] dp = new int[n + 1];

        // 第二步、初始状态
        // 空树看作是一种BST
        dp[0] = 1;

        // 第三步、状态转移
        // 考虑节点个数为i的BST数，根据有序数组[1,2,3,4.....i]
        // 可以得到以1为根节点的BST，其左子树节点个数为0，右子树节点个数为i-1
        // 以2为根节点的BST，其左子树节点个数为1，右子树节点个数为i-2
        // 以3为根节点的BST，其左子树节点个数为2，右子树节点个数为i-3
        // 以k为根节点的BST，其左子树节点个数为k-1，右子树节点个数为i-k-1d
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
