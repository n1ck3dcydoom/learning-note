package leetcode.bfs.medium;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/8/17
 * Time: 09:15
 * Description:
 */

public class _1302_DeepestLeavesSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(7);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(8);

        System.out.println(deepestLeavesSumDfs(root));
    }

    /**
     * bfs 层序遍历搜索
     */
    public static int deepestLeavesSumBfs(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                res += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }


    private static int maxDeep = 1;
    private static int res = 0;

    /**
     * dfs 深度遍历搜索
     */
    public static int deepestLeavesSumDfs(TreeNode root) {
        dfs(root, 1);
        return res;
    }

    private static void dfs(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        if (deep > maxDeep) {
            // 如果遍历到更深的层次,需要更新 maxDeep
            maxDeep = deep;
            // 同时也需要把之前累计的求和重置为第一次进入更深层次的节点值
            res = root.val;
        } else if (deep == maxDeep) {
            // 当前层次等于最深层,则累计求和
            res += root.val;
        }
        // 继续 dfs 搜索左右子树
        dfs(root.left, deep + 1);
        dfs(root.right, deep + 1);
    }
}
