package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:40
 * Description:
 */

public class _9_删除链表倒数第K个节点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 首先找到倒数第 k 个节点和他的前驱节点
        ListNode f = head;
        ListNode s = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        if (head == null) {
            return null;
        }
        while (n > 0 && f != null) {
            f = f.next;
            n--;
        }
        if (f == null && n > 0) {
            return null;
        }
        while (f != null) {
            pre = s;
            s = s.next;
            f = f.next;
        }
        // 删除 s
        pre.next = s.next;
        s = null;
        return dummy.next;
    }
}
