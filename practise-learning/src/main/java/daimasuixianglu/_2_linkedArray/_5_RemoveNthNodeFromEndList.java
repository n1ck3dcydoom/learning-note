package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _5_RemoveNthNodeFromEndList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 快慢指针
        ListNode f = head;
        ListNode s = head;
        // 快指针先走k步
        while (n-- > 0) {
            f = f.next;
        }
        // 若f==null,则说明需要删除第一个节点
        if (f == null) {
            head = head.next;
            return head;
        }
        // 快慢指针同时移动,直到最后一个节点
        while (f.next != null) {
            f = f.next;
            s = s.next;
        }
        // remove s.next
        s.next = s.next.next;
        return head;
    }
}
