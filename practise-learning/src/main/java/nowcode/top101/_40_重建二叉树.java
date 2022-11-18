package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 10:06
 * Description:
 */

public class _40_重建二叉树 {

    public static void main(String[] args) {
        TreeNode h = reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        int n = pre.length;
        return dfs(pre, vin, 0, n - 1, 0, n - 1);
    }

    private static TreeNode dfs(int[] pre, int[] vin, int pl, int pr, int vl, int vr) {
        // 重建就是根据前序的位置找到中序遍历的分割点，从分割点两侧递归构建
        if (pl > pr) {
            return null;
        }
        // 前序遍历里当前第一个节点就是此次的 root 节点
        TreeNode root = new TreeNode(pre[pl]);
        // 在中序遍历当中找到根节点的位置，将中序遍历分为左右两个部分，分别构造左右子树

        for (int i = 0; i < vin.length; i++) {
            if (pre[pl] == vin[i]) {
                // 中序遍历左半部分的节点个数和右半部分的节点个数
                int l = i - vl;
                int r = vr - i;
                // 划分 pre 和 vin 数组,构建左右子树
                root.left = dfs(pre, vin, pl + 1, pl + l, vl, vl + l - 1);
                root.right = dfs(pre, vin, pl + l + 1, pr, i + 1, vr);
            }
        }
        return root;
    }
}
