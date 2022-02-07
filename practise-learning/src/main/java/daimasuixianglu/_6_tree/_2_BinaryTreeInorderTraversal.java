package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _2_BinaryTreeInorderTraversal {

    /**
     * 递归遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(res, root.left);
        }
        res.add(root.val);
        if (root.right != null) {
            dfs(res, root.right);
        }
    }

    /**
     * 迭代遍历
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                // 一直寻找左孩子
                stack.addLast(p);
                p = p.left; // 左
            } else {
                // 左孩子为空，访问中间节点
                p = stack.pollLast();
                res.add(p.val);
                // 一直寻找中间节点的右孩子
                p = p.right;
            }
        }
        return res;
    }
}
