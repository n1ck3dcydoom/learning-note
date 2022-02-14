package daimasuixianglu._6_tree;

public class _35_InsertIntoBinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 利用递归函数的返回值，处理插入节点和父结点的关系

        // 是否需要搜索整棵BST树？
        // 明显不需要，找到插入节点的位置之后，即可立马返回
        // 即本质是查找一条合法的路径，而非遍历整棵树

        // 查找某条路经
        // if(dfs(root.left)) return
        // if(dfs(root.right)) return

        // 查找整棵树
        // left = dfs(root.left)
        // right = dfs(root.right)
        // do something with left or right

        // 递归函数的结束条件
        if (root == null) {
            return new TreeNode(val);
        }
        // 搜索左子树
        if (root.val > val) {
            // 左孩子连接到父结点上
            root.left = insertIntoBST(root.left, val);
        }
        // 搜索右子树
        else if (root.val < val) {
            // 右孩子连接到父结点上
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST1(TreeNode root, int val) {
        // 迭代法搜索BST

        if (root == null) {
            return new TreeNode(val);
        }
        // 前驱节点
        TreeNode pre = null;
        // 遍历节点
        TreeNode p = root;
        while (p != null) {
            // 记录前驱节点
            pre = p;
            // 搜索左子树
            if (p.val > val) {
                p = p.left;
            }
            // 搜索右子树
            else if (p.val < val) {
                p = p.right;
            }
        }
        // 链接插入节点和前驱节点
        p = new TreeNode(val);
        if (pre.val > val) {
            pre.left = p;
        } else if (pre.val < val) {
            pre.right = p;
        }
        return root;
    }

}
