package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 22:30
 * Description:
 */

public class _34_判断是不是二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        return dfs(root);
    }

    // 前驱节点
    private int pre = Integer.MIN_VALUE;

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        // BST 树的特征,中序遍历的结果是一个有序数组
        // 左节点的值一定小于根节点的值; 右节点的值一定大于根节点

        // 中序遍历
        // 判断左子树
        if (!dfs(root.left)) {
            return false;
        }
        // 判断上一个节点是否小于当前节点
        if (pre >= root.val) {
            return false;
        }
        // 更新前驱节点
        pre = root.val;
        // 判断右子树
        return dfs(root.right);
    }
}
