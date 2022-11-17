package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 19:31
 * Description:
 */

public class _29_二叉树和为某个值的路径_I {

    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, 0, sum);
    }

    private boolean dfs(TreeNode root, int path, int sum) {
        if (path == sum) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return dfs(root.left, path + root.val, sum) || dfs(root.right, path + root.val, sum);
    }
}
