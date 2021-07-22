package leetcode.pointoffer;

import leetcode.data.ListNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 输入两个链表，找出它们的第一个公共节点。
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/21 17:14
 **/
public class PointOffer_52 {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);
        n1.next = n2;
        ListNode n3 = new ListNode(8);
        n2.next = n3;
        ListNode n4 = new ListNode(4);
        n3.next = n4;
        ListNode n5 = new ListNode(5);
        n4.next = n5;

        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(0);
        n6.next = n7;
        ListNode n8 = new ListNode(1);
        n7.next = n8;
        n8.next = n3;

        ListNode res = getIntersectionNode(n1, n6);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 双指针
        // 先计算出长链表比短链表多出来的节点个数x
        // 然后让长链表先移动x步，使得长短链表从同一起点出发
        // 接下来同时移动两个指针，如果有公共节点，则一定会相遇，否则双指针同时到达null

        if (headA == null || headB == null) {
            return null;
        }

        int a = 0, b = 0;
        for (ListNode p = headA; p != null; p = p.next) {
            a++;
        }
        for (ListNode q = headB; q != null; q = q.next) {
            b++;
        }

        int diff = Math.abs(a - b);
        if (a > b) {
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }
        } else if (b > a) {
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        while (headA != null && headB != null) {
            if (headA.equals(headB)) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}