package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/8/22
 * Time: 09:13
 * Description:
 */

public class _655_PrintBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        System.out.println(printTree(root));
    }

    public static List<List<String>> printTree(TreeNode root) {
        // 树深
        int h = deep(root) - 1;
        int m = h + 1;
        // 2^x = 1 << x
        int n = (1 << m) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            res.add(row);
        }
        dfs(root, res, 0, (n - 1) / 2, h);
        return res;
    }

    /**
     * dfs 搜索整棵树填充矩阵
     *
     * @param root   树
     * @param res    矩阵
     * @param row    当前节点所处的行数
     * @param column 当前节点所处的列数
     * @param h      树深
     */
    private static void dfs(TreeNode root, List<List<String>> res, int row, int column, int h) {
        if (root == null) {
            return;
        }
        // 当前节点所处的矩阵行数
        res.get(row).set(column, String.valueOf(root.val));
        // 填充左子树
        dfs(root.left, res, row + 1, column - (1 << (h - row - 1)), h);
        // 填充右子树
        dfs(root.right, res, row + 1, column + (1 << (h - row - 1)), h);
    }

    /**
     * 求树的深度
     */
    private static int deep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(deep(root.left), deep(root.right)) + 1;
    }

}
