package algorithm.other;

import algorithm.structure.TreeNode;

/**
 * Created by n!Ck
 * Date: 2020/4/1
 * Time: 0:09
 * Description:
 */
public class RebuildTredd {
    private static int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
    private static int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};

    public static void main(String[] args) {
        if (preOrder == null || inOrder == null) {
            System.out.println("null tree");
        }
    }

    public static TreeNode rebuildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart,
                                       int inEnd) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 前序遍历的第一个节点为根节点
        int preRoot = preOrder[preStart];
        if (preStart == preEnd) {
            return new TreeNode(preRoot);
        }
        // 在中序遍历中找到根节点
        int inRoot;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == preRoot) {
                inRoot = i;
                break;
            }
        }
        // 构建左子树
        // rebuildTree(preOrder,preStart+1,preEnd)
        return null;
    }
}