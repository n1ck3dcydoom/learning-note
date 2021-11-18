package leetcode.tree.easy;

import leetcode.data.TreeNode;

public class _563_BinaryTreeTilt {

    public static void main(String[] args) {
        // 4,2,9,3,5,null,7
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(9);
        head.left.left = new TreeNode(3);
        head.left.right = new TreeNode(5);
        head.right.right = new TreeNode(7);

        System.out.println(findTilt(head));
    }

    private static int sum = 0;

    public static int findTilt(TreeNode root) {
        dfs(root);
        return sum;
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        sum += Math.abs(left - right);
        return left + right + root.val;
    }
}
