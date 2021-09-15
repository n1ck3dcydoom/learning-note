package algorithm.leetcode.primeDataStructure.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import algorithm.structure.TreeNode;

public class _145_BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> preorder = new ArrayList<>();
        preorder = postorderTraversal1(root);
        if (!preorder.isEmpty() && preorder.size() > 0) {
            for (int i : preorder) {
                System.out.print(i + " ");
            }
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }
        dfs(root, postorder);
        return postorder;
    }

    private static void dfs(TreeNode root, List<Integer> postorder) {
        if (root == null) {
            return;
        }
        dfs(root.left, postorder);
        dfs(root.right, postorder);
        postorder.add(root.val);
    }

    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }
        // 递归改迭代
        // 使用栈保存中间结果
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 后序遍历是，左孩子/右孩子/根节点
        // 所以入栈的顺序是相反的 根节点/右孩子/左孩子

        // root节点初始化栈
        stack.addLast(root);
        // 一直遍历栈，直到空为止
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            postorder.add(node.val);
            // 左孩子先入栈
            if (node.left != null) {
                stack.addLast(node.left);
            }
            // 右孩子入栈
            if (node.right != null) {
                stack.addLast(node.right);
            }
        }
        Collections.reverse(postorder);
        return postorder;
    }
}
