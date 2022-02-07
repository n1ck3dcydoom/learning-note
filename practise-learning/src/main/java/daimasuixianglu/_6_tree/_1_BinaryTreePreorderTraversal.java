package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _1_BinaryTreePreorderTraversal {

    /**
     * 递归遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if (root.left != null) {
            dfs(res, root.left);
        }
        if (root.right != null) {
            dfs(res, root.right);
        }
    }

    /**
     * 迭代遍历
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 根节点入栈
        stack.addLast(root);
        while (!stack.isEmpty()) {
            // 访问节点
            TreeNode node = stack.pollLast();
            res.add(node.val);
            // 右孩子先入栈，其次才是左孩子
            // 这样先访问左孩子，再访问右孩子
            // 空节点不入栈
            if (node.right != null) {
                stack.addLast(node.right);
            }
            if (node.left != null) {
                stack.addLast(node.left);
            }
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }
}
