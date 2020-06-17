package leetcode.easy.tree;

import leetcode.data.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个 N 叉树，返回其节点值的后序遍历。
 * <p>
 * 例如，给定一个 3叉树 :
 *        1
 *      / | \
 *     3  2  4
 *    / \
 *   5   6
 * 返回其后序遍历: [5,6,3,2,4,1].
 * @date 2020/6/16 0:46
 **/
public class _590_NaryTreePostorderTraversal {

    private List<Integer> res = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        dfs(root);
        return res;
    }

    private void dfs(Node root) {
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