package daimasuixianglu._6_tree;

import java.util.Deque;
import java.util.LinkedList;

public class _30_ValidateBinarySearchTree {

    private long max = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        // BST中序遍历是一个升序数组
        if (root == null) {
            return true;
        }
        // 左子树
        boolean left = isValidBST(root.left);
        // 中
        if (root.val > max) {
            // 更新中序遍历节点的，一旦发现比上次遍历的节点值小，则说明不是BST
            max = root.val;
        } else {
            return false;
        }
        // 右子树
        boolean right = isValidBST(root.right);
        return left && right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(isValidBST1(root));
    }

    public static boolean isValidBST1(TreeNode root) {
        // 中序遍历迭代法
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        long max = Long.MIN_VALUE;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (node != null) {
                // 中序遍历：左中右，入栈顺序：右中左，出栈顺序：左中右
                // 右
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                // 中
                stack.addLast(node);
                stack.addLast(null);
                // 左
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                // 空节点出队
                // queue.pollFirst();
                node = stack.pollFirst();
                // 严格递增？
                if (max >= node.val) {
                    return false;
                }
                max = node.val;
            }
        }
        return true;
    }
}
