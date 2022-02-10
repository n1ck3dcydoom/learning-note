package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class _22_FindBottomLeftTreeValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(findBottomLeftValue(root));
    }

    public static int findBottomLeftValue(TreeNode root) {
        // 直接bfs搜索最后一层
        int last = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = size;
            while (count-- > 0) {
                TreeNode node = queue.pollFirst();
                last = count + 1 == size ? node.val : last;
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return last;
    }
}
