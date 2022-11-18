package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 09:40
 * Description:
 */

public class _38_二叉树的最近公共祖先LCS {

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        return dfs(root, o1, o2);
    }

    private int dfs(TreeNode root, int o1, int o2) {
        if (root == null) {
            return -1;
        }
        // 如果 root == o1 或者 root == o2，则 root 就是 o1 和 o2 的 LCS
        if (root.val == o1 || root.val == o2) {
            return root.val;
        }
        int l = dfs(root.left, o1, o2);
        int r = dfs(root.right, o1, o2);

        // o1 和 o2 分布于 root 的左子树
        if (r == -1) {
            return l;
        }
        // o1 和 o2 分布于 root 的右子树
        else if (l == -1) {
            return r;
        }
        return root.val;
    }
}
