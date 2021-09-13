package leetcode.tree.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import leetcode.data.TreeNode;

public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        int[] preorder = new int[] { 3, 9, 20, 15, 7 };
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };

        TreeNode root = buildTree1(preorder, inorder);
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        for (TreeNode node : list) {
            System.out.print(node.val + " ");
        }
    }

    private static void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        dfs(root.left, list);
        dfs(root.right, list);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 如果递归到中序遍历结果集为空，则返回null节点
        if (inorder.length == 0) {
            return null;
        }
        // 前序遍历的第一个节点就是当前的头节点，也是划分中序遍历左右子树的中间节点
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; i++) {
            // 在中序遍历中找到前序遍历的结果
            if (inorder[i] == preorder[0]) {
                // 递归构建左子树和右子树，左闭右开的区间
                // 切割左子树的前序遍历结果时，要从当前节点的下一个节点开始切割，切到i（包括i）
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length),
                        Arrays.copyOfRange(inorder, i + 1, inorder.length));
                break;
            }
        }
        return root;
    }

    private static HashMap<Integer, Integer> indexInorderMap = new HashMap<>();

    private static TreeNode buildTree1(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            // 把中序遍历的值和所在前序遍历中的位置关系确定下来
            indexInorderMap.put(inorder[i], i);
        }
        return doBuildTree1(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode doBuildTree1(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
            int inEnd) {
        // 如果递归到遍历结果集为空，则返回null节点
        if (preStart > preEnd) {
            return null;
        }
        // 前序遍历的第一个节点就是当前的头节点，也是划分中序遍历左右子树的中间节点
        TreeNode root = new TreeNode(preorder[preStart]);
        // 得到中序遍历的分割点(前序遍历的第一个结点，在中序遍历中的索引)
        int split = indexInorderMap.get(preorder[preStart]);
        // 计算分割点左子树的节点个数
        int splitLeftSize = split - inStart;
        // 递归构建左子树和右子树
        root.left = doBuildTree1(preorder, preStart + 1, preStart + splitLeftSize, inorder, inStart, split - 1);
        root.right = doBuildTree1(preorder, preStart + splitLeftSize + 1, preEnd, inorder, split + 1, inEnd);
        return root;
    }
}
