package leetcode.tree.medium;

import leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _230_KthSmallestElementInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(kthSmallest(root, 1));
    }

    public static int kthSmallest(TreeNode root, int k) {
        // bst的中序遍历就是有序集合
        List<Integer> res = new ArrayList<>();
        dfs(res, root);

        return res.get(k - 1);
    }

    private static void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(res, root.left);
        res.add(root.val);
        dfs(res, root.right);
    }
}