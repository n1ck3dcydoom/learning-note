package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 22:09
 * Description:
 */

public class _32_合并二叉树 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return dfs(t1, t2);
    }

    private TreeNode dfs(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = dfs(t1.left, t2.left);
        merged.right = dfs(t1   .right, t2.right);
        return merged;
    }
}
