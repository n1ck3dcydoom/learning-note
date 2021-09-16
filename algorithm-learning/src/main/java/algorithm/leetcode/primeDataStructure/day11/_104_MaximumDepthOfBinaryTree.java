package algorithm.leetcode.primeDataStructure.day11;

import algorithm.structure.TreeNode;

public class _104_MaximumDepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxDepth(root));
    }

    private static int res = 0;

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 0);
        return res;
    }

    private static void dfs(TreeNode root, int h) {
        if (root == null) {
            // 更新最大深度
            res = Math.max(res, h);
            return;
        }
        // 左孩子递归
        dfs(root.left, h + 1);
        // 右孩子递归
        dfs(root.right, h + 1);
    }
}
