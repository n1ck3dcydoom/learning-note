package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 *     3
 *    / \
 *   9  20
 *  /  \
 * 15   7
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 * 返回 false 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/8 19:06
 **/
public class _110_BalancedBinaryTree {

    /**
     * 求一颗二叉树的深度，对于给定某一个根节点，其深度为左右子树中更深的度+1
     * 即 H(n) = Max( H(n左子树) , H(n右子树)
     * <p>
     * 对于叶子节点，其深度为1
     * null节点，其深度为0
     *
     * @param root 二叉树的根节点
     * @return 二叉树的深度
     */
    private int getDepth(TreeNode root) {
        // 遍历到了null节点，其深度为0
        if (root == null) {
            return 0;
        }
        // 找左子树的深度
        int left = getDepth(root.left);
        // 找右子树的深度
        int right = getDepth(root.right);
        // 返回左子树和右子树深度的最大值加1，即为当前root节点的深度
        return Math.max(left, right) + 1;
    }

    /**
     * 求left和求right以及比较diff，相当于访问节点
     * 本质是一个先序遍历算法
     * TODO 非最优解法，节点存在重复访问
     *
     * @param root 根节点
     * @return 是否时平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 当前节点左子树深度
        int left = getDepth(root.left);
        // 当前节点右子树深度
        int right = getDepth(root.right);
        // 判断深度差是否小于等于1
        int diff = left - right;
        if (diff > 1 || diff < -1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
}