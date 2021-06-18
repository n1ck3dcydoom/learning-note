package leetcode.tree.medium;

import leetcode.data.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/6/18 17:13
 **/
public class _102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 二叉树的层序搜索，使用队列保存
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 头节点放入队列
        queue.add(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 记录当前层次有多少个节点
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            // 每次遍历一层的时候全部处理完
            while (n > 0) {
                // 依次弹出队列中的节点，然后把他们的子结点按照层次顺序依次放入队列中
                // 每次处理一层后就弹出一层的所有节点，把下一层的所有节点全部放入队列
                TreeNode temp = queue.poll();
                // 这里不用判断temp是否为null，因为每次都在入队之前判断了其子结点是否为空
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
                n--;
            }
            // 把当前层的所有节点值放入结果集合当中
            res.add(list);
        }
        return res;
    }
}