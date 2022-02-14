package daimasuixianglu._6_tree;

public class _34_LowestCommonAncestorOfBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 普通二叉树
        // 后序遍历自底向上查找
        // 如果找到了p或者q，或者非空root节点，则说明此时的p或者q或者非空root节点就是LCA

        // BST树
        // 树已经有序，只需要查找到某个节点的val处于[p.val, q.val]区间内即可(或[q.val, p.val]，因为不知道p和q的相对大小)

        if (root == null) {
            return root;
        }
        // 由于不知道p和q谁大谁小，root需要同时判断
        // 此时的搜索并不需要搜索整棵树，找到一条满足条件的路径即可返回

        // 搜索边或者路径的写法（非整棵树）
        // if(dfs(root.left)) return
        // if(dfs(root.right)) return

        // 搜索整棵树的写法
        // left = dfs(root.left)
        // right = dfs(root.right)
        // do something with left or right
        // return something

        // root节点val处于p和q的区间内
        // if ((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val >
        // q.val)) {
        // return root;
        // }
        // p和q在root的某一边子树上
        if (root.val > p.val && root.val > q.val) {
            // root比p和q更大，则尝试在root的left里面查找p和q的LCA
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            // root比p和q更小，则尝试在root的right里面查找p和q的LCA
            return lowestCommonAncestor(root.right, p, q);
        }
        // 其他情况则说明root落在了[p,q]或者[q,p]当中，直接返回root节点
        return root;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // BST迭代法搜索
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return root;
    }
}
