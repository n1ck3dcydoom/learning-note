package algorithm.leetcode.primeAlgorithm.day5;

import leetcode.data.ListNode;

public class _19_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        head = removeNthFromEnd(head, 5);

        while (head!= null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 一边扫描，快慢指针
        // 删除倒数第n个数，则让快指针先走n
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        // 删除倒数第n个，即删掉第一个需要特殊处理
        if (fast == null) {
            head = head.next;
            return head;
        } else {
            // fast指向最后一个节点时，slow指向待删除节点的前驱节点
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
        }
        return head;
    }
}
