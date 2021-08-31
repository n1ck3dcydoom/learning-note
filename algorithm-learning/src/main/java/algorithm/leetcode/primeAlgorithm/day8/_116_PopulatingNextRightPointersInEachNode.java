package algorithm.leetcode.primeAlgorithm.day8;

import algorithm.structure.PerfectNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/9/2 20:08
 **/
public class _116_PopulatingNextRightPointersInEachNode {

    public static void main(String[] args) {
        PerfectNode head = new PerfectNode(1);
        head.left = new PerfectNode(2);
        head.right = new PerfectNode(3);
        head.left.left = new PerfectNode(4);
        head.left.right = new PerfectNode(5);
        head.right.left = new PerfectNode(6);
        head.right.right = new PerfectNode(7);

        connect0(head);
    }

    public static PerfectNode connect0(PerfectNode root) {
        if (root == null) {
            return null;
        }
        // 每层遍历，考虑bfs

        // 使用队列保存bfs的每层遍历节点
        Deque<PerfectNode> queue = new ArrayDeque<>();
        // 初始化队列，头节点入队
        queue.addLast(root);

        // 遍历队列
        while (!queue.isEmpty()) {
            // 当前bfs层需要遍历的节点个数
            int size = queue.size();
            // 遍历每层的节点
            for (int i = 0; i < size; i++) {
                // 头节点出队
                PerfectNode node = queue.pollFirst();
                // 队列中的节点指向下一个对头节点
                if (i + 1 == size) {
                    node.next = null;
                } else {
                    node.next = queue.peekFirst();
                }
                // 孩子节点加入队列中
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }

        return root;
    }

    public static PerfectNode connect1(PerfectNode root) {
        if (root == null) {
            return null;
        }
        // 使用队列保存bfs额外的空间复杂度是O(n)，树有多少个节点，队列就要填充多少个节点
        // 不符合常数空间的要求，递归的调用栈不计算如额外空间复杂度，考虑dfs

        // 前序遍历树时，先连接当前节点的两个子结点
        // 左孩子next指向右孩子，这一步很好实现
        if (root.left != null) {
            root.left.next = root.right;
        }
        // 右孩子next需要指向当前节点node的下一个兄弟节点的左孩子
        // dfs前序遍历中，当需要连接右孩子的next时
        // 父结点node根据前序遍历的特点，node的next节点已经指向node的下一个兄弟节点
        if (root.next != null && root.right != null) {
            root.right.next = root.next.left;
        }
        connect0(root.left);
        connect0(root.right);
        return root;
    }
}