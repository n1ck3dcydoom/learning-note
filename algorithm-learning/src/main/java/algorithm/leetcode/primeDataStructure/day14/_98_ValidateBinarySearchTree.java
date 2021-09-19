package algorithm.leetcode.primeDataStructure.day14;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.lang.model.element.Element;
import javax.xml.validation.Validator;

import algorithm.structure.TreeNode;

public class _98_ValidateBinarySearchTree {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long l, long r) {
        // 判断当前节点的值是否落在开区间(l,r)之内
        if (root == null) {
            return true;
        }
        // 左子树的节点值必须比根节点小，判断左子树时修改r = root.val
        if (root.val >= r) {
            return false;
        }
        // 右子树的节点值必须比根节点大，判断右子树时，修改l = root.val
        if (root.val <= l) {
            return false;
        }
        // 递归验证左右子树是否是bst
        return dfs(root.left, l, root.val) && dfs(root.right, root.val, r);
    }

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // bst的中序遍历是一个升序数组
        // 通过循环遍历bst，检查当前节点的值是否严格大于前一个节点的值
        // 数据范围扩大到long类型
        long pre = Long.MIN_VALUE;

        // 使用循环进行中序遍历
        // 使用 "栈" 保存中间结果
        Deque<TreeNode> queue = new ArrayDeque<>();
        while (!queue.isEmpty() || root != null) {
            // 中序遍历需要首先找到最左侧的节点
            while (root != null) {
                queue.addLast(root);
                root = root.left;
            }
            // 访问最左侧的节点
            root = queue.pollLast();
            if (pre >= root.val) {
                return false;
            }
            // 更新前一个节点的值
            pre = root.val;
            // 访问右侧节点
            root = root.right;
        }
        return true;
    }
}
