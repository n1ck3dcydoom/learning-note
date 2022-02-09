package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class _14_InvertBinaryTree {

    /**
     * bfs层序遍历，每次交换节点的左右孩子
     */
    public TreeNode invertTree0(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                // 交换左右孩子
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                // 左右孩子继续加入队列遍历
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 翻转左右孩子
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        // 递归翻转左孩子
        invertTree1(root.left);
        // 递归翻转右孩子
        invertTree1(root.right);
        return root;
    }

    /**
     * 递归先序遍历翻转
     * 考虑到递归函数的作用，输入一个节点root，返回将root翻转后的根节点
     */
    public void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        // 翻转左右孩子
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        // 递归翻转左孩子
        pre(root.left);
        // 递归翻转右孩子
        pre(root.right);
    }

    /**
     * 先序遍历迭代法
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        // 先序遍历（中左右），入栈顺序（右左中）
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.val != -1000) {
                // 右
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                // 左
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                // 中
                stack.addLast(node);
                // 加入空节点
                stack.addLast(new TreeNode(-1000));
            } else {
                // 弹出空节点
                // stack.pollLast();
                // 弹出访问节点
                TreeNode n = stack.pollLast();
                // 翻转访问节点的左右孩子
                TreeNode tmp = n.left;
                n.left = n.right;
                n.right = tmp;
            }
        }
        return root;
    }
}
