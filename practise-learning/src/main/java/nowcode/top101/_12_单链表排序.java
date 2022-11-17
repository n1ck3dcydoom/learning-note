package nowcode.top101;

import java.util.PriorityQueue;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 14:34
 * Description:
 */

public class _12_单链表排序 {

    public ListNode sortInList(ListNode head) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i, j) -> i - j);
        ListNode p = head;
        while (p != null) {
            minHeap.offer(p.val);
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            ListNode e = new ListNode(minHeap.poll());
            cur.next = e;
            cur = e;
        }
        return dummy.next;
    }
}
