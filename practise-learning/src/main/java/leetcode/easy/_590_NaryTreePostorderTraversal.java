package leetcode.easy;

import leetcode.data.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
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