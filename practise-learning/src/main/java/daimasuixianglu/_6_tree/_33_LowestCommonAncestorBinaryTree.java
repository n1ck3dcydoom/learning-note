package daimasuixianglu._6_tree;

public class _33_LowestCommonAncestorBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // LCA问题

        // 考虑递归函数的返回值作用
        // 如果当前节点root==null，返回null
        // 如果root==p或者==q，则说明在某个节点的某个子树上，查找到了节点p或者节点q
        // 此时应该返回查找到节点p或者节点q的root节点

        // 在root的左右子树中，搜索节点p和节点q，搜索完之后根绝结果再判断root是否是LCA
        // 天然的后序遍历问题（先搜索，再处理）

        // 递归函数的结束条件，查找到null或者p或者q节点
        if (root == null) {
            return null;
        } else if (root == p || root == q) {
            return root;
        }

        // 在左右子树中递归查找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果root的左右子树中都没有p或者q出现，则说明root一定不是p和q的公共祖先
        if (left == null && right == null) {
            return null;
        }
        // 考虑如下：[1,2,null,3]，寻找p=2，q=3
        // 如果root的左右子树中分别存在p和q，则root就是p和q的最近公共祖先
        // 即1.left=2，1.right=3，那么p和q的最近公共祖先就是root=1
        else if (left != null && right != null) {
            return root;
        }
        // 如果root仅有一棵子树为不为空，另一棵不为空，则说明p和q同时存在于某一棵子树当中
        // 查找1的左子树，得到1.left=2=p，返回2
        // 查找1的右子树，得到null
        // 题目保证p和q一定存在于root当中，而1.left返回了2，1.right返回了null
        // 则说明节点q=3一定存在于2的子树当中，因为p和q他们本身也可能成为最近公共祖先
        else if (left != null && right == null) {
            return left;
        } else if (right != null && left == null) {
            return right;
        }
        return null;
    }
}
