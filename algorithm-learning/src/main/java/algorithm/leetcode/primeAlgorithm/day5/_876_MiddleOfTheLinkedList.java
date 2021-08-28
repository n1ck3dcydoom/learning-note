package algorithm.leetcode.primeAlgorithm.day5;

import leetcode.data.ListNode;

public class _876_MiddleOfTheLinkedList {
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
        n4.next = null;

        ListNode mid = middleNode0(head);

        while (mid != null) {
            System.out.print(mid.val + " ");
            mid = mid.next;
        }
    }

    public static ListNode middleNode0(ListNode head) {
        // 第一次遍历得到链表的长度
        if (head == null) {
            return null;
        }
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }
        // 第二次遍历长度的一半，奇偶情况一致
        n = n / 2;
        while (n > 0) {
            head = head.next;
            n--;
        }
        return head;
    }

    public static ListNode middleNode1(ListNode head) {
        // 快慢指针，慢指针一次走一步，快指针一次走两步
        // 直到快指针走到末尾，此时慢指针指向中间节点
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 结束循环时，若fast.next == null，则说明奇数个节点，slow指向中间
        // 若fast.next != null，则说明偶数个节点，slow指向中间的前面，还需要后移一步
        if (fast.next == null) {
            return slow;
        } else {
            return slow.next;
        }
    }
}
