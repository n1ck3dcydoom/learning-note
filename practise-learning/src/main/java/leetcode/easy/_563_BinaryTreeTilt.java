package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，计算整个树的坡度。
 * <p>
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 * <p>
 * 整个树的坡度就是其所有节点的坡度之和。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * /   \
 * 2     3
 * 输出: 1
 * 解释:
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 * 注意:
 * <p>
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-tilt
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/6/10 20:34
 **/
public class _563_BinaryTreeTilt {

    private int res = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 求根节点的坡度，就必须球的左子树的坡度和右子树的坡度
     * 而求解左子树的坡度，又必须求的对应左子树和右子树的坡度
     * <p>
     * 采用自底向上的方式，每当节点返回时，立即计算其坡度
     *
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 求左子树坡度
        int left = dfs(root.left);
        // 求右子树坡度
        int right = dfs(root.right);
        // 计算坡度和
        res += Math.abs(left - right);
        // 完成左右子树的坡度计算返回其父节点时，立即计算父节点的坡度
        return left + right + root.val;
    }
}