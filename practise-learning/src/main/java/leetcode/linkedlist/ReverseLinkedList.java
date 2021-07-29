package leetcode.linkedlist;

import leetcode.data.ListNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 反转链表
 * @date 2021/7/29 19:56
 **/
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        ListNode r6 = new ListNode(6);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;

        //        ListNode head = reverse(r1);
        //        ListNode head = reverse2n(r1, 3);
        ListNode head = reverseFromN2M(r1, 3, 5);

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    /**
     * 反转一个链表
     *
     * @param head 待反转的链表
     * @return 反转后链表的头部指针
     * 1 -> 2 -> 3   head = 1
     * last = reverse(head) = 3
     * 3 -> 2 -> 1
     */
    private static ListNode reverse(ListNode head) {
        // 如果需要反转的链表没有next节点
        // 即需要反转的链表是自己本身，则直接返回自己本身
        if (head.next == null) {
            return head;
        }
        // 递归反转next节点后面的链表
        ListNode last = reverse(head.next);
        // 反转后得到last节点，由于head节点没有参与反转
        // 还需要修改head节点和反转后新链表的关系

        // 需要把head节点当作反转后新链表的尾部节点
        // 1 -> 2 <- 3
        // head = 1   last = 3
        // 首先修改新链表的最后一个节点，让它指向head，使head成为新链表的新尾部节点
        head.next.next = head;
        // 链表的尾部节点的next需要指向null
        head.next = null;
        return last;
    }

    /**
     * 反转一个链表的前n个节点
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6   head = 1, n = 3
     * <p>
     * ***************4 -> 5 -> 6
     * 1 <- 2 <- 3    ^
     * |              |
     * ----------------             newHead = 3
     */
    private static ListNode back = null;

    private static ListNode reverse2n(ListNode head, int n) {
        // 从画的图能够看出，反转前3个节点后，head = 1, last = 3
        // 但是3和4的关系断掉了，需要通过1和4关联起来
        // 所以说需要记录新链表last的后驱节点，这样才能和head关联起来

        if (n == 1) {
            // 如果说需要反转的链表就是它自己本身
            // 记录第n+1个后驱节点
            back = head.next;
            return head;
        }
        // 递归反转后面的链表
        ListNode last = reverse2n(head.next, n - 1);
        // head节点和后驱节点的关系处理同上
        head.next.next = head;
        // 让head和断掉的后驱节点链接起来
        head.next = back;
        return last;
    }

    /**
     * 反转一个链表的第m个到第n个节点，其中1<=m<=n
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6   head = 1, n = 3, m = 5
     * <p>
     * 1 -> 2 -> 5 -> 4 -> 3 -> 6
     */
    private static ListNode reverseFromN2M(ListNode head, int m, int n) {
        // 如果m==1，表示反转前n个元素
        if (m == 1) {
            return reverse2n(head, n);
        }
        // 若index(head) = 1, 则反转第m到第n个节点
        // 若index(head.next) = 1, 则反转第m-1到第n个节点
        // 若index(head.next.next) = 1, 则反转第m-2到第n-2个节点
        // 直到m==1，则反转前n个节点
        // 得到1 -> 2 -> 3 <- 4 <-5  6
        // 其中3 -> 5的后驱节点6，即3 -> 6
        // 需要把此时的head = 2和last = 5链接起来
        // 即head.next = last
        // 1 -> 2 -> 5 -> 4 -> 3 -> 6
        head.next = reverseFromN2M(head.next, m - 1, n - 1);
        return head;
    }
}