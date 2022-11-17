package nowcode.top101;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 21:53
 * Description:
 */

public class _30_BST转化为双向链表 {

    // 每次转换时的前驱节点
    private TreeNode pre = null;

    // 双向链表的头结点
    private TreeNode head = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        // 双向链表要求有序,BST 树的中序遍历是有序的,所以采用中序遍历转化
        return dfs(pRootOfTree);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = dfs(root.left);
        // 如果前驱节点为空,初始化双向链表
        if (pre == null) {
            head = root;
            pre = root;
        } else {
            // 前驱节点不为空时,与前驱节点建立关系
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        TreeNode right = dfs(root.right);

        return head;
    }
}
