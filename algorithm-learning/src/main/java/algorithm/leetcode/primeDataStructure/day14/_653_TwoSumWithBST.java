package algorithm.leetcode.primeDataStructure.day14;

import java.util.HashSet;

import algorithm.structure.TreeNode;

public class _653_TwoSumWithBST {

    private HashSet<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        // 直接遍历整棵bst，使用集合保存中间遍历的所有结果
        // 查找k-root.val是否在集合当中
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        // 递归访问左子树和右子树
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
