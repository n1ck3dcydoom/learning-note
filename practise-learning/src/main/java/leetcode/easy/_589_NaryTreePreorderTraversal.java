package leetcode.easy;

import leetcode.data.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个 N 叉树，返回其节点值的前序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 *        1
 *      / | \
 *     3  2  4
 *    / \
 *   5   6
 * <p>
 * <p>
 * 返回其前序遍历: [1,3,5,6,2,4]。
 * @date 2020/6/16 0:30
 **/
public class _589_NaryTreePreorderTraversal {

    private static List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        Node node = new Node(1);
        List<Node> child1 = new ArrayList<>();
        child1.add(new Node(3));
        child1.add(new Node(2));
        child1.add(new Node(4));
        List<Node> child2 = new ArrayList<>();
        child2.add(new Node(5));
        child2.add(new Node(6));

        node.children = child1;
        node.children.get(0).children = child2;

        res = preorder(node);
        System.out.println(res.size());
    }

    public static List<Integer> preorder(Node root) {
        dfs(root);
        return res;
    }

    private static void dfs(Node root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        if (root.children != null && root.children.size() > 0) {
            for (Node node : root.children) {
                dfs(node);
            }
        }
    }
}