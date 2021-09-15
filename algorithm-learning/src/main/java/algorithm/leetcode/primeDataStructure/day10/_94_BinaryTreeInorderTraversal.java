package algorithm.leetcode.primeDataStructure.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import algorithm.structure.TreeNode;

public class _94_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> preorder = new ArrayList<>();
        preorder = inorderTraversal2(root);
        if (!preorder.isEmpty() && preorder.size() > 0) {
            for (int i : preorder) {
                System.out.print(i + " ");
            }
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        dfs(root, inorder);
        return inorder;
    }

    private static void dfs(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        }
        dfs(root.left, inorder);
        inorder.add(root.val);
        dfs(root.right, inorder);
    }

    private static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        // 仍然使用栈保存中间节点
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 中序遍历顺序是 左孩子/根节点/右孩子
        // 中序遍历尽可能找到最左边的一个节点，这样需要把中间的节点都压入栈
        // 直到找到最左边的一个节点后，访问它
        // 然后对他的右子树继续做中序遍历
        while (!stack.isEmpty() || root != null) {
            // 尽可能找到最左边的节点
            if (root != null) {
                stack.addLast(root);
                root = root.left;
            } else {
                // 到达最左边的节点后，访问它
                TreeNode node = stack.pollLast();
                inorder.add(node.val);
                // 继续对最左边的节点的右子树做中序遍历
                root = node.right;
            }
        }
        return inorder;
    }

    /**
     * 莫里斯中序遍历
     */
    private static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        TreeNode pre = null;
        while (root != null) {
            // 如果当前节点左子树不为空
            if (root.left != null) {
                pre = root.left;
                // 找到左子树中最右侧的节点
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 把root节点和root节点的右子树，全部挂在左子树的最右侧节点上
                pre.right = root;
                TreeNode temp = root.left;
                // 断开原来root的左孩子
                root.left = null;
                // root指向它的左子树
                root = temp;
            } else {
                // 如果左子树为空，则说明到了中序遍历的最左侧端点，需要访问节点了
                inorder.add(root.val);
                // 继续处理右子树
                root = root.right;
            }
        }
        return inorder;
    }
}
