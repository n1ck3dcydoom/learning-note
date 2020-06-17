package leetcode.easy.tree;

import leetcode.data.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/5/18 22:31
 **/
public class _107_BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        postOrderTraverseTree(root, 1, list);
        list.forEach(Collections::reverse);
        Collections.reverse(list);
        return list;
    }

    /**
     * 从右子树开始后序遍历树，翻转输出每一层节点的值
     *
     * @param root  根节点
     * @param level 当前深度
     * @param list  每层节点值的list
     */
    private void postOrderTraverseTree(TreeNode root, int level, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        // 如何判断当前层的节点list有没有初始化呢？
        // 只需要判断当前list.size()和当前深度的大小即可
        if (list.size() < level) {
            List<Integer> currentList = new ArrayList<>();
            list.add(currentList);
        }
        // 从右子树开始后序遍历
        postOrderTraverseTree(root.right, level + 1, list);
        postOrderTraverseTree(root.left, level + 1, list);
        // 将当前节点值放入list中
        list.get(level - 1).add(root.val);
    }

    /**
     * 上述后序遍历后，存在两种主要翻转list的情况
     * 第一种是翻转每一层的list结果
     * 第二种是翻转最后打的list结果
     * <p>
     * 如果使用先序遍历，则不需要翻转list
     *
     * @param root  根节点
     * @param level 当前深度
     * @param list  每层节点值的list
     */
    private void preOrderTraverseTree(TreeNode root, int level, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        // 如何判断当前层的节点list有没有初始化呢？
        // 只需要判断当前list.size()和当前深度的大小即可
        if (list.size() < level) {
            List<Integer> currentList = new ArrayList<>();
            // 每次新增当前层list时，插入到头部
            list.add(0, currentList);
        }
        // 将当前节点值放入list中
        list.get(list.size() - level).add(root.val);
        preOrderTraverseTree(root.left, level + 1, list);
        preOrderTraverseTree(root.right, level + 1, list);
    }
}