package daimasuixianglu._6_tree;

public class _28_MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        // 前序遍历合并节点
        if (root1 == null) {
            root1 = new TreeNode(root2.val);
        } else if (root2 == null) {
            // root1 = root1;
        } else {
            root1.val += root2.val;
        }
        root1.left = mergeTrees(root1.left, root2.left == null ? null : root2.left);
        root1.right = mergeTrees(root1.right, root2.right == null ? null : root2.right);
        return root1;
    }

    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        // 优化剪枝
        // r1为空，返回r2
        if (root1 == null) {
            return root2;
        }
        // r2为空，返回r1
        if (root2 == null) {
            return root1;
        }
        // 两个节点都不为空，合并到r1
        root1.val += root2.val;
        // 递归合并左右子树
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);
        return root1;
    }
}
