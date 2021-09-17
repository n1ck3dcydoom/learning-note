package algorithm.leetcode.primeDataStructure.day12;

import algorithm.structure.TreeNode;

public class _226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        // 反转左右子树
        if (root == null) {
            return root;
        }
        // 反转左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归翻转左右子树
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }
}
