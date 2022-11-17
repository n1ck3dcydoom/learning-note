package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:18
 * Description:
 */

public class _6_链表有无环 {

    public boolean hasCycle(ListNode head) {
        // 快慢指针
        ListNode f = head;
        ListNode s = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                return true;
            }
        }
        return false;
    }
}
