package algorithm.leetcode.primeAlgorithm.day10;

import java.util.List;

import algorithm.structure.ListNode;

public class _21_MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode head = mergeTwoLists0(l1, l2);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

    }

    public static ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
        // 直接枚举两个链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode();
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                p.next = new ListNode(l2.val);
                l2 = l2.next;
            } else if (l2.val > l1.val) {
                p.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        // 第一步明确递归方法是做什么的
        // mergeTwoLists方法传入两个链表l1、l2，返回合并l1、l2之后的head节点
        // 那如果其中一个链表为空，自然而然返回另外一个链表
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 如果l1大于l2，那么较小的l2拿出来，让l2的next继续和l1进行合并，最后返回l2
        if (l1.val >= l2.val) {
            l2.next = mergeTwoLists1(l1.next, l2);
            return l2;
        }
        // 同上，较小的l1拿出来，让l1的next继续和l2进行合并，最后返回l1
        else {
            l1.next = mergeTwoLists1(l1, l2.next);
            return l1;
        }
    }
}
