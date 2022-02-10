package daimasuixianglu._6_tree;

public class _20_SameTree {
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 空树的情况
        if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p == null && q == null) {
            return true;
        } else {
            if (p.val != q.val) {
                return false;
            }
            // 分别比较左右子树
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
