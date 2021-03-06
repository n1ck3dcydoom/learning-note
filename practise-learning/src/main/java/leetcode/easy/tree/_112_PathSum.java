package leetcode.easy.tree;

import leetcode.data.TreeNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 *
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/12 下午10:35
 **/
public class _112_PathSum {

    /**
     * 前序遍历，每次计算还剩的路径值，直到剩余路径值==0且找到叶子节点
     *
     * @param root 树的根节点
     * @param sum  目标路径
     * @return 是否存在根节点到某个叶子节点之间的路径和等于目标路径值 true 存在 false 不存在
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        int currentRemain = sum - root.val;
        // 找到满足路径的叶子节点
        if (currentRemain == 0 && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, currentRemain) && hasPathSum(root.right, currentRemain);
    }
}