package algorithm.leetcode.primeDataStructure.day13;

import algorithm.structure.TreeNode;

public class _701_InsertIntoBinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 遇到空节点就插入
        if (root == null) {
            return new TreeNode(val);
        }
        // 在左子树中继续搜索，尝试插入到左子树节点上
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        // 在右子树中继续搜索，尝试插入到右子树节点上
        else if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
