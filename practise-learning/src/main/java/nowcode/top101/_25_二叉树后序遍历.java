package nowcode.top101;

import java.util.ArrayList;
import java.util.List;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 19:15
 * Description:
 */

public class _25_二叉树后序遍历 {

    public int[] postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.val);
    }
}
