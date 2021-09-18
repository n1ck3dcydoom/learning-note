package algorithm.leetcode.primeDataStructure.day13;

import algorithm.structure.TreeNode;

public class _700_SearchInBinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        // bst树 ，二叉搜索树的中序遍历是一个有序的升序数组
        // 其性质是左树的节点值均小于root节点，右树的节点值均大于root节点
        if (root == null) {
            return root;
        }
        if (root.val == val) {
            return root;
        }
        TreeNode left = null;
        TreeNode right = null;
        // 如果var比root小，就搜索左树
        if (root.val > val) {
            left = searchBST(root.left, val);
        }
        // 如果var比root大，就搜索右树
        if (root.val < val) {
            right = searchBST(root.right, val);
        }
        return left == null ? (right == null ? null : right) : left;
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        // bst树 ，二叉搜索树的中序遍历是一个有序的升序数组
        // 其性质是左树的节点值均小于root节点，右树的节点值均大于root节点

        // 迭代法搜索
        if (root == null) {
            return root;
        }
        if (root.val == val) {
            return root;
        }
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (root.val < val) {
                root = root.right;
            } else if (root.val > val) {
                root = root.left;
            }
        }
        return root;
    }
}
