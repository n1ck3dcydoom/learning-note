package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 15:06
 * Description:
 */

public class _14_链表奇偶重排序 {

    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);
        h.next.next.next.next.next = new ListNode(6);

        ListNode r = oddEvenList(h);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode p = head;
        ListNode h1 = new ListNode(-1);
        ListNode c1 = h1;
        ListNode h2 = new ListNode(-1);
        ListNode c2 = h2;
        int odd = 1;
        while (p != null) {
            ListNode e = new ListNode(p.val);
            if (odd++ % 2 == 1) {
                c1.next = e;
                c1 = e;
            } else {
                c2.next = e;
                c2 = e;
            }
            p = p.next;
        }
        c2 = h2.next;
        while (c2 != null) {
            c1.next = c2;
            c1 = c1.next;
            c2 = c2.next;
        }
        return h1.next;
    }
}
