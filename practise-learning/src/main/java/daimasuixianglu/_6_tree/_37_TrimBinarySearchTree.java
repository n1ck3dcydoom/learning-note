package daimasuixianglu._6_tree;

public class _37_TrimBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);

        TreeNode p = trimBST(root, 1, 2);
        int a = 1;
    }

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        // 是否需要遍历整棵树
        // 需要遍历整棵树来删除小于low和大于hight的节点

        // 递归函数的返回值，表示删除节点root之后的新根节点

        // null节点直接返回
        if (root == null) {
            return root;
        }
        // 当前节点值比区间最小值还要小
        // 根据BST的性质，root节点的左子树比root节点更小，没有查找的必要，只需要查找root的右子树
        if (root.val < low) {
            // 继续查找右子树，将右子树裁剪完成后返回的新右子树根节点作为root节点的新右孩子
            return trimBST(root.right, low, high);
            // 删掉root（让root指向裁剪后的新子树）
            // 返回裁剪后的新右子树根节点到上一层（上一层无非是root.left=xxx或者root.right=xxx）
            // 而调用入参是root.left=dfs(root.left)或者root.right=dfs(root.right)
            // 删除的节点是root.left或者root.right
            // 返回了新节点回去，那么原来的root.left就不再指向root.left而是指向新节点
            // 从而达到删除root.left的效果

        }
        // 当前节点值比区间最大值还要大
        // 根据BST的性质，root节点的右子树比root节点更大，没有查找的必要，只需要查找root的左子树
        if (root.val > high) {
            // 继续查找左子树，将左子树裁剪完成后返回的新左子树根节点作为root节点的新左孩子
            return trimBST(root.left, low, high);
            // 删掉root（让上一层的root.left或者root.right指向裁剪后的新子树）
        }
        // 当root节点的值在low和high之间，需要查找左子树和右子树
        // root.left=trimBST(root.left)相当于让root.left指向删除root.left之后的新根节点
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
