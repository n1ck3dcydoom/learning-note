package leetcode.tree.easy;

import leetcode.data.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _559_MaximumDepthNaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        List<Node> l2 = new ArrayList<>();
        l2.add(new Node(3));
        l2.add(new Node(2));
        l2.add(new Node(4));
        root.children = l2;

        List<Node> l3 = new ArrayList<>();
        l3.add(new Node(5));
        l3.add(new Node(6));
        l2.get(0).children = l3;

        System.out.println(maxDepth(root));
    }

    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        // bfs按层遍历
        //        return bfs(root);
        // dfs深度遍历
        return dfs(root);
    }

    private static int bfs(Node root) {
        // 使用队列保存中间结果
        Deque<Node> queue = new ArrayDeque<>();
        // 根节点加入队列
        queue.addLast(root);
        int dept = 0;
        // 遍历队列
        while (!queue.isEmpty()) {
            // 当前层的节点个数
            int size = queue.size();
            // 将当前层所有子节点加入队列
            while (size > 0) {
                Node node = queue.pollFirst();
                if (node.children != null && node.children.size() > 0) {
                    for (Node p : node.children) {
                        queue.addLast(p);
                    }
                }
                size--;
            }
            dept++;
        }
        return dept;
    }

    private static int dfs(Node root) {
        // dfs返回当前节点的深度
        // 求n叉树每个子结点的深度，加上1返回当前节点的深度

        int res = 0;
        if (root.children != null && root.children.size() > 0) {
            // 遍历n叉树的每个子结点
            for (Node p : root.children) {
                // 求最深的子树的深度
                res = Math.max(res, dfs(p));
            }
        }
        // 返回最深的子树深度+当前节点的深度1
        return res + 1;
    }
}
