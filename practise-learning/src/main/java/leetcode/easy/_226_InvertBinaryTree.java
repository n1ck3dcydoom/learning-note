package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/13 23:13
 **/
public class _226_InvertBinaryTree {

    /**
     * 翻转二叉树其实就是每次翻转节点的左右子树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 递归遍历左子树
        invertTree(root.left);
        // 递归遍历右子树
        invertTree(root.right);
        // 交换左右子树
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}