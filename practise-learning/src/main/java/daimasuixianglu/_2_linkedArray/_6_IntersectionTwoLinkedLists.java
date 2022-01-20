package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _6_IntersectionTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 快慢指针
        ListNode p = headA;
        ListNode q = headB;

        int ac = 0;
        while (p != null) {
            p = p.next;
            ac++;
        }
        int bc = 0;
        while (q != null) {
            q = q.next;
            bc++;
        }
        // 先让长的链表多走diff步
        int diff = Math.abs(ac - bc);
        if (ac > bc) {
            while (diff-- > 0) {
                p = p.next;
            }
        } else if (ac < bc) {
            while (diff-- > 0) {
                q = q.next;
            }
        }
        // 同时移动快慢指针
        while (p != null && q != null) {
            if (p == q) {
                return p;
            }
            p = p.next;
            q = q.next;
        }
        return null;
    }
}
