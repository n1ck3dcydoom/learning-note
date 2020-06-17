package leetcode.easy.tree;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 计算给定二叉树的所有左叶子之和。
 * <p>
 * 示例：
 * <p>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/27 23:47
 **/
public class _404_SumOfLeftLeaves {

    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        trav(root, false);
        return sum;
    }

    private void trav(TreeNode root, boolean isLeftChild) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && isLeftChild) {
            sum += root.val;
        }
        trav(root.left, true);
        trav(root.right, false);
    }
}