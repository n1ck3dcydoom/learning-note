package daimasuixianglu._6_tree;

public class _23_PathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(hasPathSum(root, 2));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return targetSum == 0;
        }
        return dfs(root, 0, targetSum);
    }

    /**
     * 递归函数，输入一个根节点root和目标值t，寻找以根节点出发到叶子节点的节点之和等于t的路径是否存在
     */
    private static boolean dfs(TreeNode root, int path, int t) {
        // 找到叶子节点后，判断当前路径是否等于t
        if (root.left == null && root.right == null) {
            return path + root.val == t;
        }
        boolean l = false;
        if (root.left != null) {
            l = dfs(root.left, path + root.val, t);
        }

        boolean r = false;
        if (root.right != null) {
            r = dfs(root.right, path + root.val, t);
        }
        return l || r;
    }
}
