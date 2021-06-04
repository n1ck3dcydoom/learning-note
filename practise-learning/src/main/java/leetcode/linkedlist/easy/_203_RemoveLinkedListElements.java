package leetcode.linkedlist.easy;

import leetcode.data.ListNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/5 1:24
 **/
public class _203_RemoveLinkedListElements {
    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(6);
        h.next.next.next = new ListNode(3);
        h.next.next.next.next = new ListNode(4);
        h.next.next.next.next.next = new ListNode(5);
        h.next.next.next.next.next.next = new ListNode(6);
        System.out.println(removeElements(h, 6));
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return new ListNode();
        }
        // 使用虚拟头节点保存头指针
        ListNode preHead = new ListNode();
        preHead.next = head;

        // 遍历链表用的指针节点
        ListNode p = preHead;
        // 遍历p，找到所有等于val的节点
        while (p.next != null) {
            if (p.next.val == val) {
                // 删除p所指的节点
                p.next = p.next.next;
            } else {
                // p后移
                p = p.next;
            }
        }
        // 虚拟头节点的next才是head节点
        return preHead.next;
    }
}