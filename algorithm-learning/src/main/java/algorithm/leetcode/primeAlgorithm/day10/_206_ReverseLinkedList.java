package algorithm.leetcode.primeAlgorithm.day10;

import algorithm.structure.ListNode;

public class _206_ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode newHead = reverseList1(head);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }

    public static ListNode reverseList0(ListNode head) {
        // 循环反转链表
        if (head == null) {
            return null;
        }
        // curr表示反转后的当前节点
        ListNode curr = null;
        // pre表示反转后curr的前驱节点
        ListNode pre = head;
        // 用于保存pre反转前下一个节点的信息
        ListNode temp;
        while (pre != null) {
            // 首先保存反转前pre的下一个节点
            temp = pre.next;
            // 反转pre节点
            pre.next = curr;
            // 反转curr节点
            curr = pre;
            // pre恢复为反转前的下一个节点
            pre = temp;
        }
        return curr;
    }

    public static ListNode reverseList1(ListNode head) {
        // 递归反转链表
        if (head.next == null) {
            return head;
        }
        // 递归的三大要素
        // 1、递归函数的作用
        // 2、递归函数的参数，变量和常量
        // 3、递归函数的返回值，拿到结果后还要再做什么

        // 1、作用：输入一个链表head，函数reverseList返回反转head后的新头节点
        ListNode next = reverseList1(head.next);
        // 3、得到反转后的next指针后，还需要反转head指针
        head.next.next = head;
        head.next = null;
        return next;
    }
}
