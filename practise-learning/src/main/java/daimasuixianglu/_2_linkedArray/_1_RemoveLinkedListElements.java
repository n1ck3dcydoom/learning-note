package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _1_RemoveLinkedListElements {

    public static void main(String[] args) {

    }

    public static ListNode removeElements(ListNode head, int val) {
        // 遍历链表
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummyHead.next;
    }
}
