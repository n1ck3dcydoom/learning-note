package daimasuixianglu._6_tree;

import java.util.ArrayList;
import java.util.List;

public class _19_BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        List<String> res = binaryTreePaths(root);
        System.out.println(1);
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> path = new ArrayList<>();
        List<String> res = new ArrayList<>();
        dfs(path, res, root);
        return res;
    }

    /**
     * 深度优先搜索+回溯
     */
    private static void dfs(List<String> path, List<String> res, TreeNode root) {
        // 找到叶子节点后结束遍历
        if (root.left == null && root.right == null) {
            // 叶子节点加入路径
            path.add(String.valueOf(root.val));
            res.add(String.join("->", path.toArray(new String[0])));
            return;
        }
        // 需要在进入左右孩子的递归遍历前，先记录父结点的信息，所以需要先序遍历
        // 递归框架
        // 记录路径
        path.add(String.valueOf(root.val));
        // 递归左孩子
        if (root.left != null) {
            dfs(path, res, root.left);
            // 回溯结果
            path.remove(path.size() - 1);
        }
        // 递归右孩子
        if (root.right != null) {
            dfs(path, res, root.right);
            // 回溯结果
            path.remove(path.size() - 1);
        }
    }

    /**
     * 深度优先搜索+回溯
     */
    private static void dfs1(String path, List<String> res, TreeNode root) {
        // 找到叶子节点后结束遍历
        if (root.left == null && root.right == null) {
            // 叶子节点加入路径
            // path.add(String.valueOf(root.val));
            path += "->" + root.val;
            res.add(path);
            return;
        }
        // 需要在进入左右孩子的递归遍历前，先记录父结点的信息，所以需要先序遍历
        // 递归框架
        // 递归左孩子
        if (root.left != null) {
            // 进入递归的时候路径带上当前选择 ("->"+root.val)
            dfs1(path + "->" + root.val, res, root.left);
            // 递归返回的时候，方法调用后的path仍然是path，而非path+"->"+root.val
        }
        // 递归右孩子
        if (root.right != null) {
            dfs1(path + "->" + root.val, res, root.right);
        }
    }
}
