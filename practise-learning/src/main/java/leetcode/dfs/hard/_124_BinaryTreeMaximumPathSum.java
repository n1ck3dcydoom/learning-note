package leetcode.dfs.hard;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/9/15
 * Time: 18:46
 * Description:
 */

public class _124_BinaryTreeMaximumPathSum {

    private static int res = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        // 考虑二叉树内任意一个节点 n,它回溯能够产生的最大路径依赖于左子树和右子树能够产生的最大路径
        // maxPath(n) = max(左子树 + n, 右子树 + n)
        // 由于需要使用到左子树和右子树的结果,采用后序遍历
        dfs(root);
        return res;
    }

    private static int dfs(TreeNode root) {
        // null 节点不产生路径,返回比数据范围更小的无意义的负数
        if (root == null) {
            return -9999;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);

        // 计算节点 root 能够产生的最大路径
        // 在计算左/右/根节点的最大路径和与权值时,完全有可能得到负数结果
        // 有以下情况可能构成最大路径和:
        // 左子树
        // 右子树
        // 左子树+根节点
        // 右子树+根节点
        // 左子树+右子树+根节点
        // 根节点

        // 包含根节点的路径的最大路径和
        // 不能同时选择两条子树,这样会出现重复路径(左-根-右)(根-右)重复了(根-右)
        int max = Math.max(root.val, Math.max(left, right) + root.val);

        res = Math.max(res,
                Math.max(root.val,
                        Math.max(left + right + root.val,
                                Math.max(right + root.val,
                                        Math.max(left + root.val,
                                                Math.max(left, right))))));
        return max;
    }
}
