package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 11:15
 * Description:
 */

public class _4_合并两个已排序链表 {

    public ListNode Merge(ListNode list1, ListNode list2) {
        // 双指针合并
        ListNode p = list1;
        ListNode q = list2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (p != null && q != null) {
            if (p.val <= q.val) {
                cur.next = new ListNode(p.val);
                p = p.next;
            } else {
                cur.next = new ListNode(q.val);
                q = q.next;
            }
            cur = cur.next;
        }
        if (p != null) {
            cur.next = p;
        }
        if (q != null) {
            cur.next = q;
        }
        return dummy.next;
    }
}
