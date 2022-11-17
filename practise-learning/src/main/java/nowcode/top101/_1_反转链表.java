package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 09:00
 * Description:
 */

public class _1_反转链表 {

    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);

        ListNode nh = reverseList(h);
        while (nh != null) {
            System.out.println(nh.val);
            nh = nh.next;
        }
    }

    /**
     * 反转链表
     * 明确递归函数的作用,输入一个链表 head,返回链表反转后的新头结点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        // 新链表的尾节点指向 head
        head.next.next = head;
        // 断开 head 和新链表的连接
        head.next = null;
        return newHead;
    }
}
