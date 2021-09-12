package algorithm.leetcode.primeDataStructure.day7;

import algorithm.structure.ListNode;

public class _203_RemoveLinkedListElements {

    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(6);
        h.next.next.next = new ListNode(3);
        h.next.next.next.next = new ListNode(4);
        h.next.next.next.next.next = new ListNode(5);
        h.next.next.next.next.next.next = new ListNode(6);

        ListNode res = removeElements(h, 6);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        // 一次扫描
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 前驱节点
        ListNode preHead = dummyHead;
        while (head != null) {
            if (head.val == val) {
                preHead.next = head.next;
                head = head.next;
            } else {
                head = head.next;
                preHead = preHead.next;
            }
        }
        return dummyHead.next;
    }
}
