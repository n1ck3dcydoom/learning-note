package daimasuixianglu._6_tree;

import java.util.ArrayList;
import java.util.List;

public class _24_PathSumII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2);
        // root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
        System.out.println(pathSum(root, -5));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        dfs(res, path, targetSum, root);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> path, int counter, TreeNode root) {
        path.add(root.val);
        counter -= root.val;
        // 找到叶子节点了
        if (root.left == null && root.right == null) {
            if (counter == 0) {
                // 注意引用的使用
                res.add(new ArrayList(path));
            }
            return;
        }
        if (root.left != null) {
            dfs(res, path, counter, root.left);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            dfs(res, path, counter, root.right);
            path.remove(path.size() - 1);
        }
    }
}
