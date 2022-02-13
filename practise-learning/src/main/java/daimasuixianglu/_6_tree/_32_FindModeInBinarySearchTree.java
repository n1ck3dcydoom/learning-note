package daimasuixianglu._6_tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _32_FindModeInBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        // root.right = new TreeNode(2);
        // root.right.left = new TreeNode(2);
        int[] res = findMode(root);
        int a = 0;
    }

    public static int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int pre = 0;
        int preCount = -1;
        int maxCount = 1;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node != null) {
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                stack.addLast(node);
                stack.addLast(null);
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                node = stack.pollLast();
                // 第一个数
                if (preCount == -1) {
                    preCount = 1;
                }
                // 与之前相同，计数器++
                else if (pre == node.val) {
                    preCount++;
                }
                // 与之前不同，重置计数器
                else {
                    preCount = 1;
                }
                // 更新之前的数
                pre = node.val;
                // 如果当前频率和之前出现的最大频率相同，则加入结果集
                if (preCount == maxCount) {
                    res.add(node.val);
                }
                // 如果当前频率大于之前出现的最大频率，更新最大频率，同时清空之前的结果集
                if (preCount > maxCount) {
                    maxCount = preCount;
                    res.clear();
                    res.add(node.val);
                }
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}
