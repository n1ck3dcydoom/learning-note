package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _4_BinaryTreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(5);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        List<Integer> res = pre(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        res = post(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        res = in(root);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    /**
     * 二叉树统一迭代访问方式
     * 前中后序访问二叉树时，入栈的顺序和访问的顺序并不相同
     * 所以使用迭代访问二叉树不像递归那样有着统一的代码风格
     * 
     * 访问顺序按照先序、中序、后序顺序操作
     * 即先序（中左右），则访问顺序（右左中）
     * 中序（左中右），访问顺序（右中左）
     * 后续（左右中），访问顺序（中右左）
     * 
     * 每次处理的节点都是“中节点”，为了保证访问顺序和处理顺序相同
     * 需要在访问过程中，对于需要处理的节点做出标记，在其后面添加空节点
     * 每次遇到空节点后，再弹出栈顶元素就是处理节点
     */
    public static List<Integer> pre(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 头节点入栈
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.val != -1000) {
                // 先序遍历（中左右），访问顺序为（右左中），出栈顺序为（中左右）
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                stack.addLast(node);
                stack.addLast(new TreeNode(-1000));
            } else {
                // 弹出空节点
                // stack.pollLast();
                // 弹出处理节点
                node = stack.pollLast();
                res.add(node.val);
            }
        }
        return res;
    }

    public static List<Integer> post(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 头节点入栈
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.val != -1000) {
                // 后序遍历（左右中），访问顺序为（中右左），出栈顺序为（中右左）
                stack.addLast(node);
                stack.addLast(new TreeNode(-1000));
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                // 弹出空节点
                // stack.pollLast();
                // 弹出处理节点
                node = stack.pollLast();
                res.add(node.val);
            }
        }
        return res;
    }

    public static List<Integer> in(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 头节点入栈
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.val != -1000) {
                // 中序遍历（左中右），访问顺序为（右中左），出栈顺序为（左中右）
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                stack.addLast(node);
                stack.addLast(new TreeNode(-1000));
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                // 弹出空节点
                // stack.pollLast();
                // 弹出处理节点
                node = stack.pollLast();
                res.add(node.val);
            }
        }
        return res;
    }
}
