package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 19:28
 * Description:
 */

public class _28_二叉树的最大深度 {

    private int max = Integer.MIN_VALUE;

    public int maxDepth(TreeNode root) {
        dfs(root, 0);
        return max;
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            max = Math.max(max, d);
            return;
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    }
}
