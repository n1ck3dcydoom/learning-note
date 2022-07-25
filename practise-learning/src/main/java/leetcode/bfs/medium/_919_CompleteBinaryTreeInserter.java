package leetcode.bfs.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import leetcode.data.TreeNode;

/**
 * Created by n!Ck
 * Date: 2022/7/25
 * Time: 23:19
 * Description:
 */

public class _919_CompleteBinaryTreeInserter {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        _919_CompleteBinaryTreeInserter test = new _919_CompleteBinaryTreeInserter(root);
        test.insert(3);
        test.insert(4);
        test.get_root();
    }

    private TreeNode root;
    private TreeNode parent;
    private Deque<TreeNode> queue = new ArrayDeque<>();

    public _919_CompleteBinaryTreeInserter(TreeNode root) {
        // 层序遍历找到第一个不完整 (孩子个数 != 2) 的父节点
        if (root == null) {
            return;
        }
        this.root = root;
        this.queue.offer(root);

        while (!this.queue.isEmpty()) {
            TreeNode node = this.queue.pollFirst();
            // 如果当前节点缺少左孩子，则他就是第一个不完整的父节点，赋值 head 之后退出循环
            if (node.left == null) {
                this.parent = node;
                break;
            }
            // 左孩子继续入队等待遍历
            this.queue.offer(node.left);
            // 如果缺少右孩子，则他就是第一个不完整的父节点，赋值 head 之后退出循环
            if (node.right == null) {
                this.parent = node;
                break;
            }
            // 右孩子继续入队等待遍历
            this.queue.offer(node.right);
        }
    }

    public int insert(int val) {
        // 保存父节点的值
        int res = parent.val;
        // 将当前新孩子节点加入队列等待遍历
        TreeNode cur = new TreeNode(val);
        this.queue.offer(cur);
        // 如果第一个不完整父节点缺少左孩子，则说明这个左孩子是下一层的第一个节点
        if (this.parent.left == null) {
            this.parent.left = cur;
            return res;
        }
        // 如果第一个不完整父节点缺少右孩子，则说明这个右孩子是下一层的某个非首位的节点
        // 此时需要重新找到最后一个不完整父节点

        // 首先先把这个不完整的父节点的右孩子补全
        this.parent.right = cur;
        // 由于之前寻找第一个不完整父节点的 bfs 不是完整的 bfs 过程
        // 当查找到第一个不完整父节点后，就退出 bfs
        // 此时队列里面的队头元素，就是下一个不完整的父节点！
        this.parent = this.queue.pollFirst();
        return res;
    }

    public TreeNode get_root() {
        return this.root;
    }
}
