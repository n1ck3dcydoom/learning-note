package leetcode.pointoffer;

import leetcode.data.ListNode;

public class PointOffer_22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        // 双指针，每次让快指针先走k步
        // 然后同时移动快慢指针，直到快指针走到null位置
        // 此时的慢指针就是倒数第k个节点
        ListNode slow = head, fast = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}