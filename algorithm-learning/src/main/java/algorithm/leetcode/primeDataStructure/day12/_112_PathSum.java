package algorithm.leetcode.primeDataStructure.day12;

import algorithm.structure.TreeNode;

public class _112_PathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        TreeNode root2 = new TreeNode(-2);
        root2.right = new TreeNode(-3);

        System.out.println(hasPathSum(root, 22));
        System.out.println(hasPathSum(root2, -5));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum);
    }

    private static boolean dfs(TreeNode root, int path, int targetSum) {
        if (root == null) {
            return false;
        }
        // 判断当前选择是否合法
        // if (path + root.val > targetSum) {
        // return false;
        // }
        // 判断是否到达叶子节点
        if (root.left == null && root.right == null) {
            // 判断选择是否满足条件
            return path + root.val == targetSum;
        }
        // 当前选择加入path，继续dfs
        return dfs(root.left, path + root.val, targetSum) || dfs(root.right, path + root.val, targetSum);
    }
}
