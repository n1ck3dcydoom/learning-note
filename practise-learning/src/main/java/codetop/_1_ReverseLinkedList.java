package codetop;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/4/20
 * Time: 21:33
 * Description:
 */

public class _1_ReverseLinkedList {

    /**
     * 迭代遍历
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        // dummy head
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        // 遍历指针
        ListNode p = head;
        // p的前驱节点
        ListNode prep = null;

        // 遍历链表
        while (p != null) {
            // 临时节点，保存p的next
            ListNode tmp = p.next;
            // 反转p指向prep
            p.next = prep;
            // 移动p和prep
            prep = p;
            p = tmp;
        }
        return prep;
    }

    /**
     * 递归反转
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return head;
        }
        return reverse(head);
    }

    /**
     * 递归函数的返回值表示反转head之后的新的头结点
     */
    private ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        // 递归反转链表，得到反转后的新链表的头结点
        ListNode rhead = reverse(head.next);

        // 反转head
        head.next.next = head;
        // 断开head.next
        head.next = null;
        // 返回新的头结点
        return rhead;
    }

}
