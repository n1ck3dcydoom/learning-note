package algorithm.leetcode.primeDataStructure.day14;

import algorithm.structure.TreeNode;

public class _235_LowestCommonAncestorOfBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        // LCA 最近公共祖先问题
        // 1、如果p和q在root的两侧，或者p==root||q==root，那么root就是lca
        // 2、如果p和q在root的同侧，则递归搜索root的某一侧

        // 2.1 都在左子树中
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 2.2 都在右子树当中
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 1 其他所有情况， p和q在root两侧，或者p==root||q==root
        else {
            // if (p == root || q == root || (p.val < root.val && q.val > root.val)
            // || (p.val > root.val && q.val < root.val)) {
            return root;
        }
    }
}
