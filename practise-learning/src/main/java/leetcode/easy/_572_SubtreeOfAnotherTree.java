package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/6/11 20:17
 **/
public class _572_SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        // 有可能t就是s
        boolean isSame = dfs(s, t);
        // 有可能t在s的左子树里面
        boolean isLeft = isSubtree(s.left, t);
        // 有可能t在s的右子树里面
        boolean isRight = isSubtree(s.right, t);
        return isSame || isLeft || isRight;
    }

    /**
     * 先序遍历查找两棵子树
     *
     * @param s
     * @param t
     * @return
     */
    private boolean dfs(TreeNode s, TreeNode t) {
        // s和t都等于null，返回true
        if (s == null && t == null) {
            return true;
        }
        // s和t有一个不为null
        if (s != null && t == null) {
            return false;
        }
        if (s == null && t != null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        boolean isLeft = dfs(s.left, t.left);
        boolean isRight = dfs(s.right, t.right);

        return isLeft && isRight;
    }
}