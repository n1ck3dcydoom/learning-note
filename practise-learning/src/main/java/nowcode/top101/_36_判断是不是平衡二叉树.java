package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 08:55
 * Description:
 */

public class _36_判断是不是平衡二叉树 {

    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树深度
        int l = depth(root.left);
        // 剪枝，左子树已经不是平衡二叉树了，不需要再判断右子树
        if (l == -1) {
            return -1;
        }
        // 右子树深度
        int r = depth(root.right);
        // 剪枝，右子树已经不是平衡二叉树了，不需要再判断当前节点
        if (r == -1) {
            return -1;
        }
        // 判断当前节点是否是平衡二叉树，左右子树的深度差不超过 1
        if (Math.abs(l - r) > 1) {
            return -1;
        }
        // 当前节点的深度为左右子树深度的最大值 +1 (加上当前节点的深度 1)
        return Math.max(l, r) + 1;
    }
}
