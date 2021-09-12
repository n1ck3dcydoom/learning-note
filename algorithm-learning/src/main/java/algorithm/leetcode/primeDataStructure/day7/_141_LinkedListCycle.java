package algorithm.leetcode.primeDataStructure.day7;

import algorithm.structure.ListNode;

public class _141_LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;
        System.out.println(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        // 没有节点或者只有一个节点的时候，肯定不存在环
        if (head == null || head.next == null) {
            return false;
        }
        // 快慢指针遍历链表
        ListNode slow = head;
        ListNode fast = head.next;
        // 快慢指针会在出现环的情况下相遇
        while (slow != fast) {
            // 如果没有环的话，快指针一定先到末尾
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
