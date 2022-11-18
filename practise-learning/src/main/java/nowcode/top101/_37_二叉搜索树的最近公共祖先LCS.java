package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 09:14
 * Description:
 */

public class _37_二叉搜索树的最近公共祖先LCS {

    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        // 比较两个节点的路径，最后一个相同的就是最近的公共祖先节点
        return dfs(root, p, q);
    }

    private int dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        // 如果 p 和 q 分布于左右两棵子树，则当前 root 就是 LCS
        if ((p >= root.val && q < root.val) || (p < root.val && q >= root.val)) {
            return root.val;
        }
        // 如果 p 和 q 同时分布于左子树或者右子树当中，则递归查找
        else if (p >= root.val && q >= root.val) {
            // 右子树
            return dfs(root.right, p, q);
        } else if (p <= root.val && q <= root.val) {
            // 左子树
            return dfs(root.left, p, q);
        }
        return -1;
    }
}
