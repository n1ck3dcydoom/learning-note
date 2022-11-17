package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 15:18
 * Description:
 */

public class _15_删除链表当中的重复元素_I {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null && p.next != null) {
            ListNode next = p.next;
            if (p.val == next.val) {
                p.next = next.next;
                next = null;
            } else {
                p = p.next;
            }
        }
        return head;
    }
}
