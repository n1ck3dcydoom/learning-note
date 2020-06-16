package leetcode.easy;


import leetcode.data.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/21 19:28
 **/
public class _100_SameTree {

    public static void main(String[] args) {

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);

        t1.left = new TreeNode(2);
        t1.right = new TreeNode(1);

        t2.left = new TreeNode(1);
        t2.right = new TreeNode(2);

        System.out.println(isSameTree(t1, t2));
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        int[] l1 = traverseTree(p);
        int[] l2 = traverseTree(q);

        if (l1.length != l2.length) {
            return false;
        }

        for (int i = 0; i < l1.length; i++) {
            if (l1[i] != l2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean traverseByFramework(TreeNode t1, TreeNode t2) {
        // 一个为空，一个不为空，肯定不相等
        if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        }
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1.val != t2.val) {
            return false;
        }
        // 只要左右子树都相同，则两棵树相同
        return traverseByFramework(t1.left, t2.left) && traverseByFramework(t1.right, t2.right);

        // 访问treeNode.val  前序遍历
        //        traverseByFramework(t1.left);
        // 访问treeNode.val  中序遍历
        //        traverseByFramework(t1.right);
        // 访问treeNode.val  后序遍历
    }

    /**
     * 中序遍历二叉树
     *
     * @param tree 二叉树
     * @return 二叉树节点值list
     */
    private static int[] traverseTree(TreeNode tree) {
        List<Integer> valList = new ArrayList<>();
        // 使用栈保存中间节点
        Stack<TreeNode> stack = new Stack<>();
        while (tree != null || !stack.isEmpty()) {
            if (tree != null) {
                stack.push(tree);
                tree = tree.left;
            } else {
                // 当tree没有左节点
                TreeNode temp = stack.pop();
                valList.add(temp.val);
                tree = temp.right;
            }
        }
        int[] result = new int[valList.size()];
        for (int i = 0; i < valList.size(); i++) {
            result[i] = valList.get(i);
        }
        return result;
    }
}