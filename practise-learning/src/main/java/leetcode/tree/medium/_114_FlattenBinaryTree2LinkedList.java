package leetcode.tree.medium;

import java.util.ArrayList;
import java.util.List;

import leetcode.data.TreeNode;

public class _114_FlattenBinaryTree2LinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        flatten1(root);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    public static void flatten(TreeNode root) {
        if (root == null || (root.left != null && root.right != null)) {
            return;
        }
        // 使用O(n)的空间保存结果
        List<TreeNode> list = new ArrayList<>();
        // 前序遍历树
        dfs(root, list);
        TreeNode newRoot = root;
        for (TreeNode node : list) {
            newRoot.left = null;
            newRoot.right = node;
            newRoot = newRoot.right;
        }
    }

    private static void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        dfs(root.left, list);
        dfs(root.right, list);
    }

    public static void flatten1(TreeNode root) {
        // 使用O(1)的空间，原地置换
        while (root != null) {
            // 如果左孩子为空，直接遍历右孩子
            if (root.left == null) {
                root = root.right;
            } else {
                // 每次把左孩子直接放到右孩子，然后把右孩子放到新右孩子的最后一个右孩子上 :
                // 保存右孩子信息
                TreeNode temp = root.right;
                // 把左孩子直接放到右孩子的位置
                root.right = root.left;
                // 找到新的右孩子的最后一个右孩子
                TreeNode right = root.right;
                while (right.right != null) {
                    right = right.right;
                }
                // 把保存的右孩子放到找到的最后一个右孩子的右孩子位置
                right.right = temp;
                // 移动root到root的右孩子继续迭代
                root.left = null;
                root = root.right;
            }
        }
    }
}
