package daimasuixianglu._6_tree;

public class _29_SearchInBinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {
        // BST搜索
        // 左树一定小于等于根节点
        // 右树一定大于等于根节点

        if (root == null) {
            return null;
        } else if (val == root.val) {
            return root;
        } else if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        // 迭代法
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return null;
    }
}
