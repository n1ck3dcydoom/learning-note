package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _3_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // 迭代法
        if (head == null) {
            return null;
        }
        ListNode cur = null;
        while (head != null) {
            // 缓存next
            ListNode temp = head.next;
            head.next = cur;
            cur = head;
            head = temp;
        }
        return cur;
    }

    public ListNode reverseList1(ListNode head) {
        // 递归法
        if (head == null || head.next == null) {
            return head;
        }
        // 明确递归函数的返回值，为反转后的链表头
        ListNode nhead = reverseList(head.next);
        // 1 -> 2 -> 3 -> 4 -> ... -> n -> n+1 -> n+2 -> ... -> n+n-2 -> n+n-1 -> n+n
        // 最后一次进入递归的时候head = n+n-1 递归函数的入参为head.next = n+n
        // 只有一个节点返回nhead就等于n+n
        // 此时的head指向nhead的末尾，更改这个反方向为nhead的末尾指向head
        // 需要注意，此时的head=n+n-1.next仍然存在，且指向n+n
        // 1 -> 2 -> 3 -> 4 -> ... -> n -> n+1 -> n+2 -> ... -> n+n-2 -> n+n-1 <-> n+n
        head.next.next = head;
        // 需要断开head.next的指针
        // 1 -> 2 -> 3 -> 4 -> ... -> n -> n+1 -> n+2 -> ... -> n+n-2 -> n+n-1 <- n+n
        head.next = null;
        return nhead;
    }
}
