package leetcode.dfs.easy;

import leetcode.data.TreeNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/17 23:32
 **/
public class _993_CousinsInBinaryTree {

    private int xd;
    private int yd;
    private TreeNode xf;
    private TreeNode yf;

    // 优化
    private boolean xFound;
    private boolean yFound;

    public boolean isCousins(TreeNode root, int x, int y) {
        // 直接遍历树，找到两个节点x和y，维护x和y的深度和对应的父节点，进行比较
        dfs(root, 0, null, x, y);
        // 判断深度相等，且父节点不相等
        return xd == yd && xf != yf;
    }

    private void dfs(TreeNode root, int h, TreeNode parentNode, int x, int y) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xd = h;
            xf = parentNode;
            xFound = true;
        }
        if (root.val == y) {
            yd = h;
            yf = parentNode;
            yFound = true;
        }
        // 如果xy已经都找到了，可以提前返回
        if (xFound && yFound) {
            return;
        }
        dfs(root.left, h + 1, root, x, y);
        // 如果xy已经都找到了，可以提前返回
        if (xFound && yFound) {
            return;
        }
        dfs(root.right, h + 1, root, x, y);
    }
}