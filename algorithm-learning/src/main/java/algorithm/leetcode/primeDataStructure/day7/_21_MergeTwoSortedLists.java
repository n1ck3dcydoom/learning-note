package algorithm.leetcode.primeDataStructure.day7;

import algorithm.structure.ListNode;

public class _21_MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        h1.next = new ListNode(2);
        h1.next.next = new ListNode(4);

        ListNode h2 = new ListNode(1);
        h2.next = new ListNode(3);
        h2.next.next = new ListNode(4);

        ListNode res = mergeTwoLists(h1, h2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummyHead = new ListNode(-1);
        ListNode head = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        } else if (l2 != null) {
            head.next = l2;
        }
        return dummyHead.next;
    }
}
