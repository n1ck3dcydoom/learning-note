package daimasuixianglu._6_tree;

public class _21_SumOfLeftLeaves {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        sumOfLeftLeaves(root);
        System.out.println(sum);
    }

    private static int sum = 0;

    /**
     * 递归函数，输入一个根节点，返回这个根节点的所有左叶子结点之和
     */
    public static void sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return;
        }
        // 判断是否存在左叶子结点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
    }
}
