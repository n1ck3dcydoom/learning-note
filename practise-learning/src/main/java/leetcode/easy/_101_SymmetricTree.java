package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 *     1
 *    / \
 *   2   2
 *    \   \
 *     3   3
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/27 0:02
 **/
public class _101_SymmetricTree {
    public static void main(String[] args) {

    }

    /**
     * 参考第100道题，两棵树是否相同
     * 遍历条件一棵从左遍历，另一棵从右遍历
     * 如果相同，则说明是对称二叉树
     *
     * @param root 树的根节点
     * @return 是否是对称二叉树
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSameTree(root.left, root.right);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // 一个为空，一个不为空，肯定不相等
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p.val != q.val) {
            return false;
        }
        // 只要左右子树都相同，则两棵树相同
        return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}