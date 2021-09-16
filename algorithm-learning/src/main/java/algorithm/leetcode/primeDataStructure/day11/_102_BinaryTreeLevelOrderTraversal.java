package algorithm.leetcode.primeDataStructure.day11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import algorithm.structure.TreeNode;

public class _102_BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = levelOrder(root);
        for (List<Integer> layerList : res) {
            for (int i : layerList) {
                System.out.print(i + " ");
            }
            System.out.println("\n");
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // 使用队列保存bfs遍历的中间结果
        Deque<TreeNode> queue = new ArrayDeque<>();
        // 根节点先入队
        queue.addLast(root);
        // 遍历队列
        while (!queue.isEmpty()) {
            // 当前层需要遍历的节点个数
            int size = queue.size();
            // 遍历当前层的所有节点
            List<Integer> layerList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 节点依次出队
                TreeNode node = queue.pollFirst();
                // 访问节点
                layerList.add(node.val);
                // 把下一层的节点放入队列
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            res.add(layerList);
        }
        return res;
    }
}
