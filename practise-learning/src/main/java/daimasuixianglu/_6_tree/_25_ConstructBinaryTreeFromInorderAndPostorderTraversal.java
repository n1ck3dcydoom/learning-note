package daimasuixianglu._6_tree;

public class _25_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public static void main(String[] args) {
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };
        int[] postorder = new int[] { 9, 15, 7, 20, 3 };
        TreeNode root = buildTree(inorder, postorder);
        int a = 1;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length, 0, postorder.length);
    }

    /**
     * 不进行数组copy，在原数组上通过下标索引操作
     * 数组索引为左闭右开区间
     */
    private static TreeNode build(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        // 后序数组为空，左闭右开区间[postStart, postEnd)
        // 当postStart == postEnd时，[postStart, postEnd)为空
        if (postStart == postEnd) {
            return null;
        }

        // 取后序数组的最后一个元素作为根节点
        TreeNode root = new TreeNode(postorder[postEnd - 1]);

        // 叶子节点不再需要切割数组和构造子树，直接返回
        // if (postEnd - postStart == 1) {
        //     return root;
        // }

        // 寻找p[postEnd - 1]在inorder的位置pos
        int pos = -1;
        // 注意左开右闭区间
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == postorder[postEnd - 1]) {
                pos = i;
                break;
            }
        }

        // 根据pos分割中序数组
        // 左子树结束位置=pos
        int leftInStart = inStart, leftInEnd = pos;
        // 右子树起始位置pos+1
        int rightInStart = pos + 1, rightInEnd = inEnd;

        // 根据根节点分割后序数组
        // 左子树结束位置=起始位置+左子树节点个数
        int leftPostStart = postStart, leftPostEnd = postStart + (leftInEnd - leftInStart);
        // 右子树起始位置=左子树结束位置（根据开闭区间决定），右子树结束位置等于最后一个节点前一个位置
        int rightPostStart = leftPostEnd, rightPostEnd = postEnd - 1;

        // 构造左子树
        root.left = build(inorder, postorder, leftInStart, leftInEnd, leftPostStart, leftPostEnd);
        // 构造右子树
        root.right = build(inorder, postorder, rightInStart, rightInEnd, rightPostStart, rightPostEnd);

        return root;
    }
}
