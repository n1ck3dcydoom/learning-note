package daimasuixianglu._6_tree;

import java.util.Deque;
import java.util.LinkedList;

public class _31_MinimumAbsoluteDifferenceInBST {

    public int getMinimumDifference(TreeNode root) {
        // BST的中序遍历是一个严格递增的序列
        // 在递增数组中，求任意两节点的最小差值，结果一定是某两个相邻节点的差值（不相邻节点的差值一定更大）

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        int pre = -1;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
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
                // 弹出空节点
                // stack.pollLast();
                node = stack.pollLast();
                // 跳过第一个元素
                if (pre == -1) {
                    // pre = node.val;
                } else {
                    min = Math.min(min, node.val - pre);
                }
                pre = node.val;
            }
        }
        return min;
    }
}
