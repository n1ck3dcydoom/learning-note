package algorithm.leetcode.primeDataStructure.day11;

import java.util.LinkedList;
import java.util.Queue;

import algorithm.structure.TreeNode;

public class _101_SymmetricTree {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root1));
        System.out.println(isSymmetric(root2));
    }

    public static boolean isSymmetric(TreeNode root) {
        // 如果根节点为空，则是镜像的
        if (root == null) {
            return true;
        }
        // 要判断一棵树是否是镜像的
        // 如果左子树和右子树是镜像的，那么根树也是镜像的
        // 判断左子树是否是镜像的
        // 需要判断左子树的左子树和左子树的右子树。。。。
        return dfs(root.left, root.right);
    }

    private static boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if ((root1 != null && root2 == null) || (root1 == null && root2 != null)) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        // 判断左树的左子树和右数的右子树
        boolean b1 = dfs(root1.left, root2.right);
        // 判断左树的右子树和右树的左子树
        boolean b2 = dfs(root1.right, root2.left);
        return b1 && b2;
    }

    public static boolean isSymmetric1(TreeNode root) {
        // bfs遍历两次，一个从左往右，一个从右往左
        return bfs(root, root);
    }

    private static boolean bfs(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> queue = new LinkedList<>();
        // 两个根节点入队
        queue.add(root1);
        queue.add(root2);

        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            // 快速失败
            if (n1 == null && n2 == null) {
                continue;
            }
            if ((n1 == null && n2 != null) || (n1 != null && n2 == null)) {
                return false;
            }
            if (n1.val != n2.val) {
                return false;
            }
            // 两个节点的孩子节点按照镜像顺序入队
            queue.add(n1.left);
            queue.add(n2.right);
            queue.add(n1.right);
            queue.add(n2.left);
        }
        return true;
    }
}
