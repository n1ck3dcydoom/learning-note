package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import leetcode.data.Node;

public class _9_N_aryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node r3 = new Node(3);
        r3.children = Arrays.asList(new Node(5), new Node(6));
        root.children = Arrays.asList(r3, new Node(2), new Node(4));

        levelOrder(root);
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.pollFirst();
                layer.add(node.val);
                if (node.children != null) {
                    for (Node n : node.children) {
                        queue.add(n);
                    }
                }
            }
            res.add(layer);
        }
        return res;
    }
}
