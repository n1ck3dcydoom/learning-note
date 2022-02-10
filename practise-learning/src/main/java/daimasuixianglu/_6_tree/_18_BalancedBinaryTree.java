package daimasuixianglu._6_tree;

public class _18_BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归查找左右子树是否是平衡二叉树
        int l = dfs(root.left);
        int r = dfs(root.right);
        if (Math.abs(l - r) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 计算一棵二叉树的高度=Max(左子树高度, 右子树高度)+1
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 由于需要比较左子树高度和右子树高度，所以需要在左右子树递归结束后再做计算
        // 采取后序遍历的方式
        // 递归框架
        int l = dfs(root.left);
        int r = dfs(root.right);
        return 1 + Math.max(l, r);
    }
}
