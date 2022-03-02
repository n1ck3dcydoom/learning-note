package daimasuixianglu._9_dp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import leetcode.data.TreeNode;

public class _19_HouseRobberIII {

    public int rob(TreeNode root) {
        // 对于二叉树，首先明确遍历顺序(dfs[前中后]，还是bfs)
        // 本题明显是后序遍历，因为不能偷两个直接相连的节点，即当前节点和两个孩子节点
        // 即对于某个节点p，需要左右孩子节点left和right
        // 只有后序遍历是先访问左右孩子，再访问根节点

        // 原始版本状态转移方程：dp[i]=max(dp[i-1], dp[i-2]+nums[i])
        // 将一维数组优化到点之后
        // 有dp=max(predp, prepredp+nums[i])
        // prepredp=predp, predp=dp
        // 递归使用全局变量记录dp结果

        // 考虑节点i偷还是不偷
        // 如果偷节点i，那么节点i的左孩子left，右孩子right都不能偷
        // 如果不偷节点i，那么节点i的左孩子left，右孩子right可以考虑（不一定偷）

        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 偷父结点
        int res1 = root.val;
        // 跳过左孩子
        if (root.left != null) {
            res1 += dfs(root.left.left) + dfs(root.left.right);
        }
        // 跳过右孩子
        if (root.right != null) {
            res1 += dfs(root.right.left) + dfs(root.right.right);
        }

        // 不偷父结点
        int res2 = 0;
        // 递归查找左孩子和右孩子的情况
        res2 += dfs(root.left) + dfs(root.right);

        // 返回两种情况的最大值
        return Math.max(res1, res2);
    }

    private int dfs(TreeNode root, Map<TreeNode, Integer> memo) {
        // 加入记忆化递归
        if (root == null) {
            return 0;
        }
        // 如果备忘录已经计算过当前节点，直接返回
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        // 偷父结点
        int res1 = root.val;
        // 跳过左孩子
        if (root.left != null) {
            res1 += dfs(root.left.left, memo) + dfs(root.left.right, memo);
        }
        // 跳过右孩子
        if (root.right != null) {
            res1 += dfs(root.right.left, memo) + dfs(root.right.right, memo);
        }

        // 不偷父结点
        int res2 = 0;
        // 递归查找左孩子和右孩子的情况
        res2 += dfs(root.left, memo) + dfs(root.right, memo);
        // 加入备忘录
        int res = Math.max(res1, res2);
        memo.put(root, res);
        return res;
    }

    private int rob1(TreeNode root) {
        int[] res = dfs2(root);
        // 返回根节点root偷或者不偷两种情况的最大值
        return Math.max(res[0], res[1]);
    }

    private int[] dfs2(TreeNode root) {
        // 树形dp
        // 对于每个节点使用数组int[2] dp记录两个状态
        // dp[0]表示不偷当前节点能够取得的最大收益
        // dp[1]表示偷当前节点能够取得的最大收益

        // 空节点偷和不偷能够取得的最大收益都是0
        if (root == null) {
            return new int[] { 0, 0 };
        }
        // 左孩子的dp数组
        int[] left = dfs2(root.left);
        // 右孩子的dp数组
        int[] right = dfs2(root.right);

        // 考虑根节点的情况，两个选择
        int[] res = new int[2];
        // 偷根节点，那么左右孩子就一定不能偷
        res[1] = root.val + left[0] + right[0];
        // 不偷根节点，那么左右孩子取最大收益
        // 左孩子偷或者不偷能够得到的最大收益+右孩子偷或者不偷能够得到的最大收益
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return res;
    }
}
