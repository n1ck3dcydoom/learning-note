package leetcode.bfs.medium;

import leetcode.data.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1609_EvenOddTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.left.left = new TreeNode(12);
        root.left.left.right = new TreeNode(8);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);

        System.out.println(isEvenOddTree(root));
    }

    public static boolean isEvenOddTree(TreeNode root) {
        // bfs遍历
        Deque<TreeNode> queue = new ArrayDeque<>();
        // 头节点入队
        queue.addLast(root);
        // 记录层数
        int h = 0;
        // 根据性质，奇数层所有节点都是偶数，且严格递减
        // 偶数层所有节点都是奇数，且严格递增
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = queue.peek().val;
            boolean odd = h % 2 != 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (odd && node.val % 2 != 0) {
                    return false;
                }
                if (!odd && node.val % 2 == 0) {
                    return false;
                }
                if (i != 0) {
                    // 从第二个节点开始判断单调性
                    if (odd) {
                        if (node.val >= pre) {
                            return false;
                        }
                    }
                    if (!odd) {
                        if (node.val <= pre) {
                            return false;
                        }
                    }
                }
                // 下一层所有节点入队
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
                // 更新前一个节点
                pre = node.val;
            }
            h++;
        }
        return true;
    }
}
