package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 22:01
 * Description:
 */

public class _31_对称二叉树 {

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return false;
        }
        // 判断一棵树是否是对称二叉树
        // 两次前序遍历,只不过第二次方向相反
        return dfs(pRoot.left, pRoot.right);
    }

    private boolean dfs(TreeNode l, TreeNode r) {
        // 两个都是空节点返回 true
        if (l == null && r == null) {
            return true;
        }
        // 一个为空一个不为空返回 false
        if ((l == null && r != null) || (l != null && r == null)) {
            return false;
        }
        // 两个节点的值不相同返回 false
        if (l.val != r.val) {
            return false;
        }
        // 两个都不为空,比较左右子树
        return dfs(l.left, r.right) && dfs(l.right, r.left);
    }
}
