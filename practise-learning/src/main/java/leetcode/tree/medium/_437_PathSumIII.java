package leetcode.tree.medium;

import leetcode.data.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _437_PathSumIII {

    public static void main(String[] args) {
        // [10,5,-3,3,2,null,11,3,-2,null,1]
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);

        System.out.println(pathSum0(root, 8));
    }

    private static int res = 0;

    public static int pathSum0(TreeNode root, int targetSum) {
        // 使用前缀和优化路径查找
        dfs(new ArrayDeque<>(), root, targetSum);
        return res;
    }

    private static void dfs(Deque<Integer> preSum, TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        preSum.addLast(preSum.peekLast() + root.val);
        int curSum = preSum.peekLast();
        preSum.stream().forEach(i -> i - ro);
        for (int sum : preSum) {
            if (curSum - sum == targetSum) {
                res++;
            }
        }
        // 继续dfs
        dfs(preSum, root.left, targetSum);
        dfs(preSum, root.right, targetSum);
        // 撤销选择
        preSum.remove(preSum.size() - 1);
    }

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        dfs(0, root, targetSum);
        if (root.left != null) {
            pathSum(root.left, targetSum);
        }
        if (root.right != null) {
            pathSum(root.right, targetSum);
        }
        return res;
    }

    private static void dfs(int path, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        // 当前路径加入选择
        path += root.val;
        // 判断路径是否达到目标
        if (path == target) {
            res++;
        }
        // 继续dfs搜索左子树
        dfs(path, root.left, target);
        // 继续dfs搜索右子树
        dfs(path, root.right, target);
        // 撤销选择
        path -= root.val;
    }
}