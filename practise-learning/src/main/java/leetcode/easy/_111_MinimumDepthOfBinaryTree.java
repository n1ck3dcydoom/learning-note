package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/11 20:52
 **/
public class _111_MinimumDepthOfBinaryTree {


    /**
     * 求二叉树的最大深度
     * <p>
     * 所有的递归都需要考虑递归的结束条件
     * 1. 当root节点的左右子树都是null时，return 1
     * 2. 当root节点有一个子树为null时，return 另外一个子树节点的深度
     * 3. 当root节点的两个子树都不为null时，return 左右子树深度更大的值+1 （这里的1指的是root节点本身的深度）
     * <p>
     * 递归有个很重要的两点：
     * 1. 明确递归函数的作用
     * 2. 确定递归返回的条件
     * <p>
     * 这个递归函数的作用：计算当前节点的最大深度
     * 入参：树的某个节点
     * 出参：树的某个节点的深度
     * <p>
     * 什么时候递归返回：
     * 当访问到null节点时（即当前节点的左右子树都为null）时
     * null节点的深度肯定为0，此时返回（找到了叶子节点）
     * <p>
     * 如果当前节点不是null节点，则需要递归访问该节点的左右子树
     * 该节点的深度 = 其左右子树的更大深度值 + 1
     * maxDepth(root) = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
     *
     * @param root 二叉树
     * @return 最大深度
     */
    private int maxDepth(TreeNode root) {
        // 遍历到了null节点，其深度为0
        if (root == null) {
            return 0;
        }
        // 遍历到了叶子节点，叶子节点的深度为1
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 找左子树的深度
        int left = maxDepth(root.left);
        // 找右子树的深度
        int right = maxDepth(root.right);
        // 返回左子树和右子树深度的最大值加1，即为当前root节点的深度
        return Math.max(left, right) + 1;
    }

    /**
     * 上述代码是求二叉树的最大深度，如果把max改成min，是不是能满足求最小深度呢
     * 错！
     * 因为最小深度的定义是从根节点到 “叶子节点” ，而叶子节点是没有左右子树的
     * 对于以下情况
     * 1
     * /
     * 2
     * 在遍历节点1时，出现错误情况，发现其右子树为空，所以返回0，然后结束递归返回最小深度1
     * <p>
     * 在上述函数中，结束递归的条件只有左子树和右子树都为null时，返回0
     * <p>
     * 而本题中的递归终止条件不再是左右子树==null的情况
     * 需要考虑以下方面：
     * 1. root节点为null，return 0
     * 2. 左子树为null，return 右子树的深度 + 1
     * 3. 右子树为null，return 左子树的深度 + 1
     * 4. 左右子树都不为null，return min(左子树深度，右子树深度）+ 1
     */
    public int minDepth(TreeNode root) {
        // 情况1  root节点为null，return 0
        if (root == null) {
            return 0;
        }
        // 情况2  左子树为null，return 右子树的深度 + 1
        if (root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        }
        // 情况3  右子树为null，return 左子树的深度 + 1
        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 情况4  左右子树都不为null，return min(左子树深度，右子树深度）+ 1
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left, right) + 1;
    }
}