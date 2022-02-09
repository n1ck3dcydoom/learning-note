package daimasuixianglu._6_tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class _16_SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        // 节点为空的情况
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        }
        // 左右节点都不为空，但是数值不相等
        else if (left.val != right.val) {
            return false;
        }
        // 访问两棵树，按照左右中，右左中的顺序分别比较
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric1(root));
    }

    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // bfs搜索
        Deque<TreeNode> queue = new ArrayDeque<>();
        // 左右子树都先加入队列
        queue.addLast(root.left);
        queue.addLast(root.right);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode ln = queue.pollFirst();
                TreeNode rn = queue.pollFirst();
                // 两个空节点继续比较
                if (ln == null && rn == null) {
                    continue;
                }
                // 比较两个对称节点
                if (ln == null && rn != null) {
                    return false;
                } else if (ln != null && rn == null) {
                    return false;
                } else if (ln.val != rn.val) {
                    return false;
                }
                // 加入对称孩子节点
                queue.addLast(ln.left);
                queue.addLast(rn.right);
                queue.addLast(ln.right);
                queue.addLast(rn.left);
            }
        }
        return true;
    }
}
