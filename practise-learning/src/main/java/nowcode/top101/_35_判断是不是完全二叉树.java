package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 08:34
 * Description:
 */

public class _35_判断是不是完全二叉树 {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        // bfs 搜索
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        // 判断是否遇到了空节点
        // 这里的 flag 要放到外面,如果每层遍历开始前初始化会导致空洞在最后一个位置时判断错误
        boolean flag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.pollFirst();
                if (node.val == -9999) {
                    // 遇到空节点了
                    flag = true;
                    continue;
                }
                if (flag) {
                    return false;
                }
                // 下一层的节点无论是否存在，都把当前左右孩子加入
                // 空节点将会作为是否是完全二叉树的判断条件
                // java Deque 不支持 null 入队，所以用 val == -9999 表示空节点
                if (node.left != null) {
                    queue.offerLast(node.left);
                } else {
                    queue.offerLast(new TreeNode(-9999));
                }
                if (node.right != null) {
                    queue.offerLast(node.right);
                } else {
                    queue.offerLast(new TreeNode(-9999));
                }
            }
        }
        return true;
    }
}
