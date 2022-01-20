package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _7_LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        // 快慢指针
        ListNode f = head;
        ListNode s = head;
        // 先找链表有没有环
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            // 相遇了,说明有环
            if (s == f) {
                // 从相遇点和头节点开始同时出发,走n-1圈后再次相遇即为环入口
                f = head;
                while (s != f) {
                    f = f.next;
                    s = s.next;
                }
                return s;
            }
        }
        return null;
    }
}
