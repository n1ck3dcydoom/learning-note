package leetcode.easy;

import leetcode.data.TreeNode;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 *  
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/28 21:27
 **/
public class _543_DiameterOfBinaryTree {

    private int res = 0;

    /**
     * 以任意一个节点为根节点，经过这个节点的最长路径为，其左子树的最大深度+右子树的最大深度
     * 所以只需要递归遍历每个节点求解最大路径长度，通过全局变量比较每个节点的最大路径长度
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 当前节点左子树的最大深度
        int left = dfs(root.left);
        // 当前节点右子树的最大深度;
        int right = dfs(root.right);
        // 比较当前节点的最长路径
        res = Math.max(res, left + right);
        // 返回当前子树深度
        return Math.max(left, right) + 1;
    }
}