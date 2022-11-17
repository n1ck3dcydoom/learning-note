package nowcode.top101;

import java.util.ArrayList;
import java.util.PriorityQueue;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 11:21
 * Description:
 */

public class _5_合并k个已排序的链表 {

    public ListNode mergeKLists(ArrayList<ListNode> lists) {

        // 优先队列
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i, j) -> j - i);
        for (ListNode list : lists) {
            while (list != null) {
                minHeap.offer(list.val);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            cur.next = new ListNode(minHeap.poll());
            cur = cur.next;
        }
        return dummy.next;

        // 分治
        // if (lists == null || lists.size() == 0) {
        //     return null;
        // }
        // return mergeKListsRange(lists, 0, lists.size() - 1);
    }

    private ListNode mergeKListsRange(ArrayList<ListNode> lists, int i, int j) {
        // 分治两两合并
        if (i == j) {
            return lists.get(i);
        }
        if (i > j) {
            return null;
        }
        int mid = i + (j - i) / 2;
        return merge(mergeKListsRange(lists, i, mid), mergeKListsRange(lists, mid + 1, j));
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        // 双指针合并
        ListNode p = l1;
        ListNode q = l2;
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
