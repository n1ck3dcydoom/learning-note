package daimasuixianglu._6_tree;

public class _39_ConvertBstToGreaterTree {

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        // 累加树，反中序遍历
        // 中序遍历：左中右
        // 反中序遍历：右中左

        // 递归函数需要返回值吗？
        // 不需要，直接遍历整棵树然后操作root节点即可
        // 需要pre指针记录root节点的上一个节点值

        // 结束递归的条件
        if (root == null) {
            return null;
        }
        // 右
        convertBST(root.right);
        // 修改root节点的值为上一个节点和当前节点的和
        root.val = root.val + sum;
        sum = root.val;
        // 左
        convertBST(root.left);

        return root;
    }
}
