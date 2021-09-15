package algorithm.leetcode.primeDataStructure.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import algorithm.structure.TreeNode;

public class _144_BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> preorder = new ArrayList<>();
        preorder = preorderTraversal1(root);
        if (!preorder.isEmpty() && preorder.size() > 0) {
            for (int i : preorder) {
                System.out.print(i + " ");
            }
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }
        dfs(root, preorder);
        return preorder;
    }

    private static void dfs(TreeNode root, List<Integer> preorder) {
        if (root == null) {
            return;
        }
        preorder.add(root.val);
        dfs(root.left, preorder);
        dfs(root.right, preorder);
    }

    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }
        // 递归改迭代
        // 使用栈保存中间结果
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 前序遍历是，根节点/左孩子/右孩子
        // 所以入栈的顺序是相反的右孩子/左孩子/根节点

        // root节点初始化栈
        stack.addLast(root);
        // 一直遍历栈，直到空为止
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            preorder.add(node.val);
            // 右孩子先入栈
            if (node.right != null) {
                stack.addLast(node.right);
            }
            // 左孩子入栈
            if (node.left != null) {
                stack.addLast(node.left);
            }
        }
        return preorder;
    }
}
