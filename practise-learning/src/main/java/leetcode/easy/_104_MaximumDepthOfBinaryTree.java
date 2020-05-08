package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 *      3
 *     / \
 *    9  20
 *   / \
 *  15  7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/27 23:28
 **/
public class _104_MaximumDepthOfBinaryTree {
    private static int currentDeep = 0;
    private static int temp = 0;

    public static void main(String[] args) {
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(9);
        t.right = new TreeNode(20);
        t.left.left = null;
        t.left.right = null;
        t.right.left = new TreeNode(15);
        t.right.right = new TreeNode(7);
        System.out.println(maxDepth(t));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverseByFramework(root);
        return currentDeep;
    }

    public static void traverseByFramework(TreeNode t) {
        // 前序遍历访问节点
        if (t == null) {
            return;
        }
        temp++;
        currentDeep = currentDeep > temp ? currentDeep : temp;
        traverseByFramework(t.left);
        // 中序遍历访问节点
        traverseByFramework(t.right);
        temp--;
        // 后序遍历访问节点
    }

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
}