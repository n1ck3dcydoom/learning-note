package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 22:25
 * Description:
 */

public class _33_镜像输出二叉树 {

    public TreeNode Mirror(TreeNode pRoot) {
        // 翻转二叉树
        dfs(pRoot);
        return pRoot;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 交换左右孩子
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        dfs(root.left);
        dfs(root.right);
    }
}
