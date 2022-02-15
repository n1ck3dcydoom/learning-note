package daimasuixianglu._6_tree;

public class _26_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    /**
     * 使用索引访问数组，不额外构造数组
     */
    private TreeNode build(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        // 坚持左闭右开的原则
        // [preStart, preEnd)区间为空
        if (preStart == preEnd) {
            return null;
        }

        // 根据先序遍历的第一个数构造根节点
        TreeNode root = new TreeNode(preorder[preStart]);

        // 找到根节点在中序遍历的位置
        int pos = -1;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                pos = i;
                break;
            }
        }

        // 切割中序遍历数组
        // 左子树
        int leftInStart = inStart, leftInEnd = pos;
        // 右子树，排除掉pos元素
        int rightInStart = pos + 1, rightInEnd = inEnd;

        // 切割先序遍历数组
        // 左子树，起始位置排除掉根节点，结束位置=起始位置+左子树节点个数
        int leftPreStart = preStart + 1, leftPreEnd = leftPreStart + (leftInEnd - leftInStart);
        // 右子树，左闭右开
        int rightPreStart = leftPreEnd, rightPreEnd = preEnd;

        // 构造左子树
        root.left = build(preorder, inorder, leftPreStart, leftPreEnd, leftInStart, leftInEnd);
        // 构造右子树
        root.right = build(preorder, inorder, rightPreStart, rightPreEnd, rightInStart, rightInEnd);

        return root;
    }
}
