package algorithm.leetcode.primeAlgorithm.day8;

import algorithm.structure.TreeNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/2 19:19
 **/
public class _617_MergeTwoBinaryTrees {
    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(1);
        head1.left = new TreeNode(3);
        head1.right = new TreeNode(2);
        head1.left.left = new TreeNode(5);

        TreeNode head2 = new TreeNode(2);
        head2.left = new TreeNode(1);
        head2.right = new TreeNode(3);
        head2.left.right = new TreeNode(4);
        head1.right.right = new TreeNode(7);

        TreeNode mergedHead = mergeTrees(head1, head2);
        System.out.println(1);
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 合并当前节点
        TreeNode mergedNode = new TreeNode(root1.val + root2.val);
        // 前序遍历两棵树
        // dfs合并左子树
        mergedNode.left = mergeTrees(root1.left, root2.left);
        // dfs合并右子树
        mergedNode.right = mergeTrees(root1.right, root2.right);

        return mergedNode;
    }
}