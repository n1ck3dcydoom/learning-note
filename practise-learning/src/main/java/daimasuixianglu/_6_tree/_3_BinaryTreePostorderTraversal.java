package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class _3_BinaryTreePostorderTraversal {

    /**
     * 递归遍历
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            dfs(res, root.left);
        }
        if (root.right != null) {
            dfs(res, root.right);
        }
        res.add(root.val);
    }

    /**
     * 迭代遍历
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 头节点入栈
        stack.addLast(root);
        // 先序遍历的顺序是，中左右
        // 后序遍历的顺序是，左中右
        // 在栈里面调整左孩子和右孩子的入栈顺序，使得先序遍历的顺序变为，中右左
        // 然后将数组逆序输出得到，左右中，即最终后序遍历的结果
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 访问头节点
            res.add(node.val);
            // 左孩子先入栈
            if (node.left != null) {
                stack.addLast(node.left);
            }
            // 右孩子再入栈，这样出栈访问的顺序就是先右后左
            if (node.right != null) {
                stack.addLast(node.right);
            }
        }
        // 最终访问顺序即，中右左
        // 逆序后的到后序遍历的顺序，左右中
        Collections.reverse(res);
        return res;
    }
}
